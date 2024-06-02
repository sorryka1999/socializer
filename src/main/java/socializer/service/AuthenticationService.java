package socializer.service;

import socializer.configuration.SecretKeyProvider;
import socializer.dto.auth.AuthRequestDto;
import socializer.dto.auth.AuthResponseDto;
import socializer.entity.PortalUserEntity;
import socializer.exception.CustomWrongInputException;
import socializer.repository.PortalUserRepository;
import socializer.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static socializer.constant.WrongInputType.NO_USER_WITH_EMAIL_OR_PASSWORD;
import static socializer.constant.WrongInputType.USER_IS_DISABLED;

@RequiredArgsConstructor
@Service
public class AuthenticationService {

    private final PortalUserRepository portalUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final SecretKeyProvider secretKeyProvider;

    public ResponseEntity<AuthResponseDto> authorize(AuthRequestDto dto) {
        PortalUserEntity portalUserEntity = portalUserRepository.findByEmail(dto.email());

        checkPassword(dto.password(), portalUserEntity);
        checkUserIsActive(portalUserEntity);

        String token = JwtUtil.generateToken(
                secretKeyProvider.getSecretKey(),
                portalUserEntity.getId()
        );
        return ResponseEntity.ok(new AuthResponseDto(token));
    }

    private void checkPassword(String providedPassword, PortalUserEntity portalUserEntity) {
        if (
                Objects.isNull(portalUserEntity) ||
                !passwordEncoder.matches(providedPassword, portalUserEntity.getPassword())
        )
            throw new CustomWrongInputException(NO_USER_WITH_EMAIL_OR_PASSWORD);
    }

    private void checkUserIsActive(PortalUserEntity portalUserEntity) {
        if (!portalUserEntity.getIsActive())
            throw new CustomWrongInputException(USER_IS_DISABLED);
    }

}
