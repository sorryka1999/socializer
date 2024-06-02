package socializer.repository;

import socializer.entity.PortalUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PortalUserRepository extends JpaRepository<PortalUserEntity, Long> {

    PortalUserEntity findByEmail(String email);

}
