package socializer.controller;

import socializer.constant.Endpoint;
import socializer.dto.auth.AuthRequestDto;
import socializer.dto.auth.AuthResponseDto;
import socializer.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Tag(name = "AUTHENTICATION")
@RequiredArgsConstructor
@RestController
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @Operation(summary = "authentication")
    @ApiResponse(responseCode = "200", description = "Success", content = @Content(
            schema = @Schema(implementation = AuthResponseDto.class)
    ))
    @PostMapping(path = Endpoint.AUTHENTICATE, produces = APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<AuthResponseDto> authentication(
            @RequestBody @Valid AuthRequestDto dto
    ) {
        return authenticationService.authorize(dto);
    }

}
