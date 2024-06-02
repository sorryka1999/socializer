package socializer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import socializer.entity.PortalUserEntity;
import socializer.entity.QuestionEntity;
import socializer.entity.ResultEntity;

import java.util.List;

@Repository
public interface ResultRepository extends JpaRepository<ResultEntity, Long> {
    ResultEntity findByPortalUserAndQuestion(PortalUserEntity portalUser, QuestionEntity question);
    List<ResultEntity> findByPortalUserAndQuestionIn(PortalUserEntity portalUser, List<QuestionEntity> question);
}
