package socializer.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record UpdatePortalUserDto(
        @Size(min = 5, max = 63)
        @Email
        String email,
        @Size(max = 63)
        String fullName
) {}
