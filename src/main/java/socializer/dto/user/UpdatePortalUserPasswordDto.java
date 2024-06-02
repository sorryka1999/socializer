package socializer.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdatePortalUserPasswordDto(
        @NotBlank
        @Size(min = 8, max = 32)
        String oldPassword,
        @NotBlank
        @Size(min = 8, max = 32)
        String newPassword
) {}
