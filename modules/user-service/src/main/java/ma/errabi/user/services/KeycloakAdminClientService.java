package ma.errabi.user.services;

import lombok.RequiredArgsConstructor;
import ma.errabi.user.config.KeycloakProperties;
import ma.errabi.user.domain.User;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class KeycloakAdminClientService {
    private final KeycloakProperties keycloakProperties;
    private final RestTemplate restTemplate;

    private String getAdminToken() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
        form.add("grant_type", "password");
        form.add("client_id", keycloakProperties.getClientId());
        form.add("username", keycloakProperties.getUsername());
        form.add("password", keycloakProperties.getPassword());

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(form, headers);

        ResponseEntity<Map> response = restTemplate.postForEntity(
                keycloakProperties.getUrl() + "/realms/" + keycloakProperties.getRealm() + "/protocol/openid-connect/token",
                request,
                Map.class
        );

        return "Bearer " + response.getBody().get("access_token").toString();
    }

    public void createUserInKeycloak(User user, String password) {
        String token = getAdminToken();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token.replace("Bearer ", ""));

        Map<String, Object> body = new HashMap<>();
        body.put("username", user.getUsername());
        body.put("email", user.getEmail());
        body.put("enabled", true);
        body.put("firstName", user.getFirstName());
        body.put("lastName", user.getLastName());
        body.put("credentials", List.of(Map.of(
                "type", "password",
                "value", password,
                "temporary", false
        )));

        Map<String, Object> attributes = new HashMap<>();
        attributes.put("userId", List.of(String.valueOf(user.getId())));
        body.put("attributes", attributes);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);
        restTemplate.postForEntity(
                keycloakProperties.getUrl() + "/admin/realms/" + keycloakProperties.getRealm() + "/users",
                entity,
                String.class
        );
    }
}

