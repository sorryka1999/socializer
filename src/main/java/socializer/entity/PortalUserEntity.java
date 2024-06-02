package socializer.entity;

import socializer.entity.enums.PortalUserType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "portal_user")
public class PortalUserEntity implements UserDetails {

    @Id
    @SequenceGenerator(name = "portal_user_id_generator", sequenceName = "portal_user_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "portal_user_id_generator")
    private Long id;
    private String email;
    private String password;
    @Column(name = "full_name")
    private String fullName;
    @Enumerated(EnumType.STRING)
    @Column(name = "portal_user_type")
    private PortalUserType portalUserType;
    @Column(name = "is_active")
    private Boolean isActive;
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(portalUserType);
    }

    @Override
    public String getUsername() {
        return email;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    }
}
