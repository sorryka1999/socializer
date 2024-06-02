package socializer.dto.question;

import jakarta.validation.constraints.NotNull;
import socializer.util.openai.EmotionType;

public record QuestionRequestDto(
        @NotNull
        Boolean random,
        @NotNull
        EmotionType emotionType
) {}
