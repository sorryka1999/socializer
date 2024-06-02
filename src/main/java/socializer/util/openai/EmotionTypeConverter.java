package socializer.util.openai;

import org.springframework.core.convert.converter.Converter;

public class EmotionTypeConverter implements Converter<String, EmotionType> {

    @Override
    public EmotionType convert(String source) {
        try {
            return EmotionType.valueOf(source.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
