//package com.identity.integration;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.ObjectWriter;
//import com.identity.TestData;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//public class RolesIntegrationTest {
//    @Autowired
//    private MockMvc mvc;
//    private Data data = new Data();
//    private String token = "Bearer ";
//    private final String tokenName = "Authorization";
//
//    @BeforeEach
//    public void beforeEach() throws Exception {
//        token += data.generateToken();
//    }
//
//    @Test
//    public void getAllRoles() throws Exception {
//        mvc.perform(get("/roles?page=1&perPage=4")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .header("Authorization", "Bearer " + token)
//                )
//                .andExpect(status().isOk())
//                .andExpect(content()
//                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
//    }
//
//    @Test
//    public void SaveRoleTest() throws Exception {
//        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
//
//        mvc.perform(post("/roles")
//                        .header("Authorization", "Bearer " + token)
//                        .contentType(MediaType.APPLICATION_JSON)
////                .param(ow.writeValueAsString(TestData.getRoleDto()))
//                        .contentType(ow.writeValueAsString(TestData.getRoleDto()))
//        );
//    }
//}
//
