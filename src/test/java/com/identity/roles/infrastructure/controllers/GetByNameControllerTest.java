package com.identity.roles.infrastructure.controllers;

import com.identity.TestData;
import com.identity.roles.aplication.services.GetByName;
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
class GetByNameControllerTest {
    @Mock
    private GetByName service;
    private GetByNameController underTest;
    private String name;

    @BeforeEach
    void setUp() {
        underTest = new GetByNameController(service);
        name = TestData.getNewRole().getName();
    }

    @Test
    void get() {
        ArgumentCaptor<String> nameCaptor = ArgumentCaptor.forClass(String.class);
        underTest.get(name);
        verify(service, atLeastOnce()).get(nameCaptor.capture());
        assertThat(nameCaptor.getValue()).isEqualTo(name);
    }
}