package socializer.util.openai;

import lombok.experimental.UtilityClass;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import socializer.dto.question.QuestionRequestDto;
import socializer.dto.question.QuestionResponseDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@UtilityClass
public class GenerateQuestionUtil {

    private static final String MODEL = "gpt-3.5-turbo";
    private static final String OPEN_API_KEY = "";
    private static final String OPEN_API_URL = "https://api.openai.com/v1/chat/completions";

    public static ResponseEntity<QuestionResponseDto> generate(QuestionRequestDto dto) {
        EmotionType emotionType = dto.random() ? getRandomEmotionType() : dto.emotionType();

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add((request, body, execution) -> {
            request.getHeaders().add("Authorization", "Bearer " + OPEN_API_KEY);
            return execution.execute(request, body);
        });

        ChatResponse response = restTemplate.postForObject(
                OPEN_API_URL,
                new ChatRequest(MODEL, emotionType.getPrompt()),
                ChatResponse.class
        );

        boolean hasNoResponse = response == null ||
                response.getChoices() == null ||
                response.getChoices().isEmpty();

        String question = hasNoResponse ? "No response" : response.getChoices().get(0).getMessage().getContent();

        return ResponseEntity.ok(new QuestionResponseDto(question, emotionType));
    }

    private static EmotionType getRandomEmotionType() {
        List<EmotionType> emotionTypeList = new ArrayList<>();
        emotionTypeList.add(EmotionType.ANGER);
        emotionTypeList.add(EmotionType.DISGUST);
        emotionTypeList.add(EmotionType.ENJOYMENT);
        emotionTypeList.add(EmotionType.FEAR);
        emotionTypeList.add(EmotionType.SADNESS);
        emotionTypeList.add(EmotionType.CONTEMPT);

        Random random = new Random();
        int randomIndex = random.nextInt(6);
        return emotionTypeList.get(randomIndex);
    }
}
