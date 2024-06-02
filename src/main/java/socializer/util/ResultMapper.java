package socializer.util;

import lombok.experimental.UtilityClass;
import socializer.dto.result.QuestionDto;
import socializer.dto.result.ResultDto;
import socializer.entity.ResultEntity;

@UtilityClass
public class ResultMapper {

    public static ResultDto mapFromEntityToDto(ResultEntity entity) {
        QuestionDto question = new QuestionDto(
                entity.getQuestion().getId(),
                entity.getQuestion().getName()
        );
        return new ResultDto(
                question,
                entity.getAnswer()
        );
    }
}
