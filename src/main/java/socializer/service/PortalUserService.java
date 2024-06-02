package socializer.service;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import socializer.constant.WrongInputType;
import socializer.dto.MessageDto;
import socializer.dto.user.*;
import socializer.entity.PortalUserEntity;
import socializer.entity.enums.PortalUserType;
import socializer.exception.CustomWrongInputException;
import socializer.repository.PortalUserRepository;
import socializer.util.PortalUserMapper;

import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PortalUserService {

    private final PortalUserRepository portalUserRepository;
    private final ResultService resultService;
    private final QuestionService questionService;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    public PortalUserEntity getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (PortalUserEntity) authentication.getPrincipal();
    }

    public ResponseEntity<PortalUserResponseDto> create(CreatePortalUserDto dto) {
        checkEmailIsUsed(dto.email());

        PortalUserEntity portalUserEntity =
                PortalUserMapper.mapFromDtoToEntity(dto, PortalUserType.USER, passwordEncoder);

        portalUserRepository.save(portalUserEntity);
        PortalUserResponseDto portalUserResponseDto =
                PortalUserMapper.mapFromEntityToDto(portalUserEntity);

        resultService.create(
                portalUserEntity,
                questionService.getAll()
        );
        return ResponseEntity.ok(portalUserResponseDto);
    }

    public ResponseEntity<PortalUserResponseDto> update(UpdatePortalUserDto dto) {
        PortalUserEntity portalUserEntity = getAuthenticatedUser();

        updateEmail(portalUserEntity, dto.email());

        portalUserEntity.setFullName(
                Optional.ofNullable(dto.fullName())
                        .orElse(portalUserEntity.getFullName())
        );

        portalUserEntity = portalUserRepository.save(portalUserEntity);
        PortalUserResponseDto portalUserResponseDto =
                PortalUserMapper.mapFromEntityToDto(portalUserEntity);

        return ResponseEntity.ok(portalUserResponseDto);
    }

    public ResponseEntity<PortalUserResponseDto> updatePassword(UpdatePortalUserPasswordDto dto) {
        PortalUserEntity portalUserEntity = getAuthenticatedUser();
        validatePassword(portalUserEntity, dto.oldPassword());

        portalUserEntity.setPassword(passwordEncoder.encode(dto.newPassword()));

        portalUserEntity = portalUserRepository.save(portalUserEntity);
        PortalUserResponseDto portalUserResponseDto =
                PortalUserMapper.mapFromEntityToDto(portalUserEntity);

        return ResponseEntity.ok(portalUserResponseDto);
    }

    public ResponseEntity<PortalUserResponseDto> delete() {
        PortalUserEntity authenticatedUser = getAuthenticatedUser();

        resultService.delete(
                authenticatedUser,
                questionService.getAll()
        );

        portalUserRepository.delete(authenticatedUser);
        PortalUserResponseDto responseDto = PortalUserMapper.mapFromEntityToDto(authenticatedUser);

        return ResponseEntity.ok(responseDto);
    }

    public ResponseEntity<PortalUserResponseDto> get() {
        PortalUserEntity entity = getAuthenticatedUser();
        PortalUserResponseDto dto = PortalUserMapper.mapFromEntityToDto(entity);
        return ResponseEntity.ok(dto);
    }

    public ResponseEntity<MessageDto> resetPassword(ResetPortalUserPasswordDto dto) {
        PortalUserEntity entity = portalUserRepository.findByEmail(dto.email());
        if (entity == null)
            return ResponseEntity.ok(new MessageDto("Email is sent!"));

        String newPassword = RandomStringUtils.random(16, true, true);
        entity.setPassword(passwordEncoder.encode(newPassword));

        portalUserRepository.save(entity);
        emailService.sendEmail(
                dto.email(),
                "Password reset",
                String.format("New password: %s", newPassword));

        return ResponseEntity.ok(new MessageDto("Email is sent!"));
    }



    private void checkEmailIsUsed(String email) {
        UserDetails userDetails = portalUserRepository.findByEmail(email);
        if (Objects.nonNull(userDetails))
            throw new CustomWrongInputException(WrongInputType.EMAIL_IS_ALREADY_IN_USE);
    }

    private void updateEmail(PortalUserEntity entity, String newEmail) {
        if (Objects.isNull(newEmail) || entity.getEmail().equals(newEmail))
            return;

        checkEmailIsUsed(newEmail);
        entity.setEmail(newEmail);
    }

    private void validatePassword(PortalUserEntity entity, String providedPassword) {
        if (!passwordEncoder.matches(providedPassword, entity.getPassword()))
            throw new CustomWrongInputException(WrongInputType.WRONG_PASSWORD);
    }

}
