package socializer.entity.enums;

import org.springframework.core.convert.converter.Converter;

public class PortalUserTypeConverter implements Converter<String, PortalUserType> {

    @Override
    public PortalUserType convert(String source) {
        try {
            return PortalUserType.valueOf(source.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
