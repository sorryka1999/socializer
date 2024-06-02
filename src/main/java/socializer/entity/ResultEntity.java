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
@Table(name = "result")
public class ResultEntity {

    @Id
    @SequenceGenerator(name = "result_id_generator", sequenceName = "result_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "result_id_generator")
    private Long id;
    private String answer;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "portal_user")
    private PortalUserEntity portalUser;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question")
    private QuestionEntity question;

}
