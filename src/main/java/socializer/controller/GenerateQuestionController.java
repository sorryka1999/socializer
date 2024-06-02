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
import socializer.dto.question.QuestionRequestDto;
import socializer.dto.question.QuestionResponseDto;
import socializer.util.openai.GenerateQuestionUtil;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Tag(name = "GENERATE QUESTION")
@RequiredArgsConstructor
@RestController
public class GenerateQuestionController {

    @Operation(summary = "get generated question")
    @ApiResponse(responseCode = "200", description = "Success", content = @Content(
            schema = @Schema(implementation = QuestionResponseDto.class)
    ))
    @PostMapping(path = Endpoint.GENERATE_QUESTION, produces = APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<QuestionResponseDto> getGeneratedQuestion(
            @RequestBody @Valid QuestionRequestDto dto
    ) {
        return GenerateQuestionUtil.generate(dto);
    }

}
