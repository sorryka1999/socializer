package socializer.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import socializer.constant.Endpoint;
import socializer.dto.user.CreatePortalUserDto;
import socializer.dto.user.PortalUserResponseDto;
import socializer.service.PortalUserService;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Tag(name = "REGISTRATION")
@RequiredArgsConstructor
@RestController
@RequestMapping(Endpoint.REGISTRATION)
public class RegistrationController {

    private final PortalUserService portalUserService;

    @Operation(summary = "create portal user")
    @ApiResponse(responseCode = "200", description = "Success", content = @Content(
            schema = @Schema(implementation = PortalUserResponseDto.class)
    ))
    @PostMapping(produces = APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<PortalUserResponseDto> registration(
            @RequestBody @Valid CreatePortalUserDto dto
    ) {
        return portalUserService.create(dto);
    }
}
