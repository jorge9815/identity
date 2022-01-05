package com.identity.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.identity.TestData;
import com.identity.roles.aplication.RoleDto;
import com.identity.shared.PaginatedList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class RolesIntegrationTest {
    @Autowired
    private MockMvc mvc;
    private String token = "Bearer ";
    private final String tokenName = "Authorization";
    private RoleDto role = TestData.getRoleDto();
    ObjectWriter ow = new ObjectMapper().writer()
            .withDefaultPrettyPrinter();

    @BeforeEach
    public void beforeEach() throws Exception {
        token += TestData.generateToken();
    }

    @Test
    public void getAllRoles() throws Exception {
        saveRole(role);
        mvc.perform(configureRequest(get("/roles?page=1&perPage=4")))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(ow
                        .writeValueAsString(new PaginatedList<RoleDto>(1l, 1,4,List.of(role)))));
    }

    @Test
    public void SaveRoleTest() throws Exception {
        saveRole(role);
    }

    @Test
    public void GetByNameTest() throws Exception {
        saveRole(role);
        mvc.perform(configureRequest(get("/role?name=" + role.getName()))
        ).andExpect(content()
                .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(ow.writeValueAsString(role))
                );
    }

    @Test
    public void getByIdTest() throws Exception {
        saveRole(role);
        mvc.perform(configureRequest(get("/role/"+role.getId())))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(ow.writeValueAsString(role)));
    }



    private void saveRole(RoleDto role) throws Exception {
        mvc.perform(configureRequest(post("/roles"))
                .content(ow.writeValueAsString(role))
        );
    }

    private MockHttpServletRequestBuilder configureRequest(MockHttpServletRequestBuilder httpMethod) {
        return httpMethod
                .contentType(MediaType.APPLICATION_JSON)
                .header(tokenName, token);
    }

}



