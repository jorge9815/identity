package com.identity.users.infrastructure.controllers;

import com.identity.TestData;
import com.identity.users.aplication.services.GetByUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class GetByUserControllerTest {
    @Mock
    private GetByUser service;
    private GetByUserController underTest;
    private String user;

    @BeforeEach
    void setUp() {
        underTest = new GetByUserController(service);
        user = TestData.getUser().getUser();
    }

    @Test
    void get() {
        ArgumentCaptor<String> userCaptor = ArgumentCaptor.forClass(String.class);
        underTest.get(user);
        verify(service, atLeastOnce()).get(userCaptor.capture());
        assertThat(userCaptor.getValue()).isEqualTo(user);
    }
}