package socializer.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import socializer.constant.Endpoint;
import socializer.dto.user.*;
import socializer.service.PortalUserService;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Tag(name = "PORTAL USER")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
@RestController
@RequestMapping(Endpoint.PORTAL_USER)
public class PortalUserController {

    private final PortalUserService portalUserService;

    @Operation(summary = "update portal user")
    @ApiResponse(responseCode = "200", description = "Success", content = @Content(
            schema = @Schema(implementation = PortalUserResponseDto.class)
    ))
    @PostMapping(path = Endpoint.UPDATE, produces = APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<PortalUserResponseDto> updatePortalUser(
            @RequestBody @Valid UpdatePortalUserDto dto
    ) {
        return portalUserService.update(dto);
    }

    @Operation(summary = "update password")
    @ApiResponse(responseCode = "200", description = "Success", content = @Content(
            schema = @Schema(implementation = PortalUserResponseDto.class)
    ))
    @PostMapping(path = Endpoint.UPDATE_PASSWORD, produces = APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<PortalUserResponseDto> updatePassword(
            @RequestBody @Valid UpdatePortalUserPasswordDto dto
    ) {
        return portalUserService.updatePassword(dto);
    }

    @Operation(summary = "delete portal user")
    @ApiResponse(responseCode = "200", description = "Success", content = @Content(
            schema = @Schema(implementation = PortalUserResponseDto.class)
    ))
    @PostMapping(path = Endpoint.DELETE, produces = APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<PortalUserResponseDto> deletePortalUser() {
        return portalUserService.delete();
    }

    @Operation(summary = "get portal user")
    @ApiResponse(responseCode = "200", description = "Success", content = @Content(
            schema = @Schema(implementation = PortalUserResponseDto[].class)
    ))
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<PortalUserResponseDto> getPortalUser() {
        return portalUserService.get();
    }

}
