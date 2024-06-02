package socializer.dto.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AuthRequestDto(
        @NotBlank
        @Size(max = 63)
        String email,
        @NotBlank
        @Size(max = 60)
        String password
) {}
