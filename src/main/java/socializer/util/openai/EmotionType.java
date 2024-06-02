package socializer.util.openai;

import lombok.Getter;

@Getter
public enum EmotionType {
    ANGER("Explain a real life social scenario in one sentence where person feel anger. Do not use the word anger. That sentence should be addressed to me."),
    DISGUST("Explain a real life social scenario in one sentence where person feel disgust. Do not use the word disgust. That sentence should be addressed to me."),
    ENJOYMENT("Explain a real life social scenario in one sentence where person feel enjoy. Do not use the word enjoy. That sentence should be addressed to me."),
    FEAR("Explain a real life social scenario in one sentence where person feel fear. Do not use the word fear. That sentence should be addressed to me."),
    SADNESS("Explain a real life social scenario in one sentence where person feel sad. Do not use the word sad. That sentence should be addressed to me."),
    CONTEMPT("Explain a real life social scenario in one sentence where person feel contempt. Do not use the word contempt. That sentence should be addressed to me.");

    private final String prompt;

    EmotionType(String prompt) {
        this.prompt = prompt;
    }
}
