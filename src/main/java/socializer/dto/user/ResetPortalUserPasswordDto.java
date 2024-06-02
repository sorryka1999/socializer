package socializer.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ResetPortalUserPasswordDto(
        @NotBlank
        @Size(min = 5, max = 63)
        @Email
        String email
) {}
