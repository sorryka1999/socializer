package socializer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import socializer.entity.QuestionEntity;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<QuestionEntity, Long> {
    List<QuestionEntity> findByIdIn(List<Long> idList);
}
