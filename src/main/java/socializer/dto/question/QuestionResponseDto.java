package socializer.dto.question;

import socializer.util.openai.EmotionType;

public record QuestionResponseDto(
        String question,
        EmotionType emotionType
) {}
