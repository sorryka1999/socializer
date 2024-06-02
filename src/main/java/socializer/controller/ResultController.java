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
import socializer.dto.result.ChangeResultDto;
import socializer.dto.result.ResultDto;
import socializer.service.ResultService;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Tag(name = "RESULT")
@RequiredArgsConstructor
@RestController
@RequestMapping(Endpoint.RESULT)
public class ResultController {

    private final ResultService resultService;

    @Operation(summary = "get all results")
    @ApiResponse(responseCode = "200", description = "Success", content = @Content(
            schema = @Schema(implementation = ResultDto[].class)
    ))
    @GetMapping(path = Endpoint.GET_ALL, produces = APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<List<ResultDto>> getAllResults() {
        return resultService.getAll();
    }

    @Operation(summary = "set result")
    @ApiResponse(responseCode = "200", description = "Success", content = @Content(
            schema = @Schema(implementation = ResultDto.class)
    ))
    @PostMapping(path = Endpoint.SET, produces = APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<List<ResultDto>> setResult(
            @RequestBody @Valid ChangeResultDto dto
    ) {
        return resultService.update(dto);
    }

}
