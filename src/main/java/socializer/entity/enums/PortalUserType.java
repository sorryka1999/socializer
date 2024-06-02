package socializer.entity.enums;

import org.springframework.security.core.GrantedAuthority;

public enum PortalUserType implements GrantedAuthority {
    ADMIN,
    USER;

    @Override
    public String getAuthority() {
        return this.name();
    }
}
