package socializer.util;

import socializer.dto.user.CreatePortalUserDto;
import socializer.dto.user.PortalUserResponseDto;
import socializer.entity.PortalUserEntity;
import lombok.experimental.UtilityClass;
import org.springframework.security.crypto.password.PasswordEncoder;
import socializer.entity.enums.PortalUserType;

import java.time.LocalDateTime;

@UtilityClass
public class PortalUserMapper {

    public static PortalUserEntity mapFromDtoToEntity(CreatePortalUserDto dto, PortalUserType type, PasswordEncoder passwordEncoder) {
        return new PortalUserEntity(
                null,
                dto.email(),
                passwordEncoder.encode(dto.password()),
                dto.fullName(),
                type,
                true,
                LocalDateTime.now()
        );
    }

    public static PortalUserResponseDto mapFromEntityToDto(PortalUserEntity entity) {
        return new PortalUserResponseDto(
                entity.getId(),
                entity.getEmail(),
                entity.getFullName(),
                entity.getPortalUserType(),
                entity.getIsActive(),
                entity.getCreatedAt()
        );
    }

}
