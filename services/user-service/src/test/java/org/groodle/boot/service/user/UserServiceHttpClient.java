package org.groodle.boot.service.user;

import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import static io.cucumber.spring.CucumberTestContext.SCOPE_CUCUMBER_GLUE;

@Component
@Scope(SCOPE_CUCUMBER_GLUE)
public class UserServiceHttpClient {

    private final String SERVER_URL = "http://localhost";
    private final String USER_SERVICE_ENDPOINT = "/v1/users";

    @LocalServerPort
    private int port;
    private final RestTemplate restTemplate = new RestTemplate();

    private String userServiceEndpoint() {
        return SERVER_URL + ":" + port + USER_SERVICE_ENDPOINT;
    }

    public void create(){
        restTemplate.postForEntity(userServiceEndpoint(),"",String.class).getStatusCode();
    }

}
