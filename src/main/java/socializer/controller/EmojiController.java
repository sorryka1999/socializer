package socializer.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import socializer.constant.Endpoint;
import socializer.service.EmojiService;

import static org.springframework.http.MediaType.APPLICATION_OCTET_STREAM_VALUE;

@Tag(name = "EMOJI")
@RequiredArgsConstructor
@RestController
public class EmojiController {

    private final EmojiService emojiService;

    @Operation(summary = "test")
    @ApiResponse(responseCode = "200", description = "Success", content = @Content(
            schema = @Schema(implementation = Resource.class)
    ))
    @GetMapping(path = Endpoint.EMOJI, produces = APPLICATION_OCTET_STREAM_VALUE)
    public @ResponseBody ResponseEntity<Resource> getEmoji(
            @RequestParam String emoji
    ) {
        return ResponseEntity.ok(
                emojiService.getEmoji(emoji)
        );
    }

}
