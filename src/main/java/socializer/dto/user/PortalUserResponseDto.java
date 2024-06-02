package socializer.dto.user;

import socializer.entity.enums.PortalUserType;

import java.time.LocalDateTime;

public record PortalUserResponseDto(
        Long id,
        String email,
        String fullName,
        PortalUserType type,
        Boolean isActive,
        LocalDateTime createdAt
) {}
