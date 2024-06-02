package socializer.controller;

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
import socializer.constant.Endpoint;
import socializer.dto.MessageDto;
import socializer.dto.user.ResetPortalUserPasswordDto;
import socializer.service.PortalUserService;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Tag(name = "PASSWORD RESET")
@RequiredArgsConstructor
@RestController
public class EmailController {

    private final PortalUserService portalUserService;

    @Operation(summary = "password reset")
    @ApiResponse(responseCode = "200", description = "Success", content = @Content(
            schema = @Schema(implementation = MessageDto.class)
    ))
    @PostMapping(path = Endpoint.RESET_PASSWORD, produces = APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<MessageDto> passwordReset(
            @RequestBody @Valid ResetPortalUserPasswordDto dto
    ) {
        return portalUserService.resetPassword(dto);
    }

}
