package socializer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import socializer.constant.WrongInputType;
import socializer.entity.QuestionEntity;
import socializer.exception.CustomWrongInputException;
import socializer.repository.QuestionRepository;

import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    public QuestionEntity get(Long id) {
        try {
            return questionRepository.findById(id)
                    .orElseThrow();
        } catch (NoSuchElementException e) {
            throw new CustomWrongInputException(WrongInputType.NO_QUESTION_WITH_THIS_ID);
        }
    }

    public List<QuestionEntity> getAll() {
        return questionRepository.findAll();
    }
}
