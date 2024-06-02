package socializer.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreatePortalUserDto(
        @NotBlank
        @Size(min = 5, max = 63)
        @Email
        String email,
        @NotBlank
        @Size(min = 8, max = 32)
        String password,
        @NotBlank
        @Size(max = 63)
        String fullName
) {}
