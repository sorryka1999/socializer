package socializer.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition
@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(info());
    }

    private Info info() {
        return new Info()
                .title("STUDY-PORTAL")
                .description("STUDY-PORTAL university learning platform")
                .version("V1.0")
                .contact(contact());
    }

    private Contact contact() {
        return new Contact()
                .name("Sardor Khurramov")
                .email("sorryka1999@gmail.com");
    }
}
