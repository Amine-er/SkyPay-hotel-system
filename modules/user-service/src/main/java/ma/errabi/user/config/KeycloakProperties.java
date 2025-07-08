package ma.errabi.user.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "keycloak.admin")
public class KeycloakProperties {
    private String realm;
    private String url;
    private String clientId;
    private String username;
    private String password;
}
