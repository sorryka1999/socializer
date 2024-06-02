package socializer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import socializer.dto.result.ChangeResultDto;
import socializer.dto.result.ResultDto;
import socializer.entity.QuestionEntity;
import socializer.entity.PortalUserEntity;
import socializer.entity.ResultEntity;
import socializer.repository.QuestionRepository;
import socializer.repository.ResultRepository;
import socializer.util.ResultMapper;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ResultService {

    private final ResultRepository resultRepository;
    private final QuestionRepository questionRepository;
    private final QuestionService questionService;

    private PortalUserEntity getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (PortalUserEntity) authentication.getPrincipal();
    }

    public void create(PortalUserEntity user, List<QuestionEntity> questionEntityList) {
        List<ResultEntity> resultEntityList = questionEntityList.stream()
                .map(question -> new ResultEntity(null, "", user, question))
                .toList();

        resultRepository.saveAll(resultEntityList);
    }

    public ResponseEntity<List<ResultDto>> update(ChangeResultDto dto) {
        PortalUserEntity authenticatedUser = getAuthenticatedUser();

        List<Long> answerIdList = dto.answers()
                .stream()
                .map(answerDto -> answerDto.questionId())
                .toList();
        List<QuestionEntity> questionEntityList = questionRepository.findByIdIn(answerIdList);

        List<ResultEntity> resultEntityList = resultRepository
                .findByPortalUserAndQuestionIn(authenticatedUser, questionEntityList);

        dto.answers()
                .forEach(answerDto -> {
                    ResultEntity result = resultEntityList.stream()
                            .filter(resultEntity -> resultEntity.getQuestion().getId().equals(answerDto.questionId()))
                            .findFirst()
                            .orElseThrow();
                    result.setAnswer(answerDto.answer());
                });
        resultRepository.saveAll(resultEntityList);

        List<ResultDto> resultDtoList = resultEntityList.stream()
                .map(ResultMapper::mapFromEntityToDto)
                .toList();
        return ResponseEntity.ok(resultDtoList);
    }

    public void delete(PortalUserEntity user, List<QuestionEntity> questionEntityList) {
        List<ResultEntity> resultEntityList = resultRepository.findByPortalUserAndQuestionIn(user, questionEntityList);
        resultRepository.deleteAll(resultEntityList);
    }

    public ResponseEntity<List<ResultDto>> getAll() {
        PortalUserEntity authenticatedUser = getAuthenticatedUser();
        List<QuestionEntity> questionEntityList = questionService.getAll();

        List<ResultDto> resultDtoList = questionEntityList.stream()
                .map(question -> resultRepository.findByPortalUserAndQuestion(authenticatedUser, question))
                .map(ResultMapper::mapFromEntityToDto)
                .toList();

        return ResponseEntity.ok(resultDtoList);
    }

}
