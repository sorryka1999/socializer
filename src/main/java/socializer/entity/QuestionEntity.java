package socializer.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "question")
public class QuestionEntity {

    @Id
    @SequenceGenerator(name = "question_id_generator", sequenceName = "question_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "question_id_generator")
    private Long id;
    private String name;
}
