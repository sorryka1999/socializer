package socializer.dto.result;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record ChangeResultDto(
        @NotNull
        List<AnswerDto> answers
) {
        public record AnswerDto(
                @NotNull
                Long questionId,
                @NotBlank
                String answer
        ) {}
}
