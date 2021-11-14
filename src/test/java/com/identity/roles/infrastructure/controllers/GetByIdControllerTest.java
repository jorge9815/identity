package com.identity.roles.infrastructure.controllers;

import com.identity.TestData;
import com.identity.roles.aplication.services.GetByID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class GetByIdControllerTest {
    @Mock
    private GetByID service;
    private GetByIdController underTest;
    private String id;

    @BeforeEach
    void setUp() {
        underTest = new GetByIdController(service);
        id = TestData.getRoleId().getValue();
    }

    @Test
    void get() {
        ArgumentCaptor<String > idCaptor = ArgumentCaptor.forClass(String.class);
        underTest.get(id);
        verify(service, atLeastOnce()).get(idCaptor.capture());
        assertThat(idCaptor.getValue()).isEqualTo(id);
    }
}