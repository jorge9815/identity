package com.identity.integration;


import com.identity.IdentityApplication;
import com.identity.TestData;
import com.identity.roles.aplication.RoleDto;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SpringBootTest(
        classes = IdentityApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
public class RolesIntegrationTest {
    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();
    HttpHeaders headers = new HttpHeaders();


    @Test
    public void saveRoleTest(){
        HttpEntity<RoleDto> entity = new HttpEntity<RoleDto>(new RoleDto(TestData.getNewRole()), headers);
        ResponseEntity<String> response = restTemplate
                .exchange(createURLWithPort("roles"), HttpMethod.POST, entity,String.class);
        String responseStatus = response.getStatusCode().toString();
        assertThat(responseStatus).isEqualTo("Created");
    }

private String createURLWithPort(String uri){
    return "http://localhost:"+port+uri;
}
}

