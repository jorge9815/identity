package com.identity.users.infrastructure.controllers;

import com.identity.TestData;
import com.identity.users.aplication.services.GetByUserAndPassword;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ByUserAndPasswordPostControllerTest {
    @Mock
    private GetByUserAndPassword service;
    private ByUserAndPasswordPostController underTest;
    private String user;
    private String pass;

    @BeforeEach
    void setUp() {
        underTest = new ByUserAndPasswordPostController(service);
        pass = TestData.getNewUserDto().getPassword();
        user = TestData.getNewUserDto().getUser();
    }

    @Test
    void getByPassAndUser() throws Exception {
        ArgumentCaptor<String> userCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> passCaptor = ArgumentCaptor.forClass(String.class);

        underTest.getByPassAndUser(user, pass);
        verify(service, atLeastOnce()).get(userCaptor.capture(), passCaptor.capture());
        assertThat(userCaptor.getValue()).isEqualTo(user);
        assertThat(passCaptor.getValue()).isEqualTo(pass);
    }
}