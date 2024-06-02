package socializer.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import socializer.constant.Endpoint;
import socializer.dto.MessageDto;
import socializer.service.PortalUserService;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Tag(name = "TEST")
@RequiredArgsConstructor
@RestController
@RequestMapping(Endpoint.TEST)
public class TestController {

    private final PortalUserService portalUserService;

    @Operation(summary = "test")
    @ApiResponse(responseCode = "200", description = "Success", content = @Content(
            schema = @Schema(implementation = MessageDto.class)
    ))
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<MessageDto> test() {
        return ResponseEntity.ok(new MessageDto("successful"));
    }

}
