package com.identity.roles.infrastructure.controllers;

import com.identity.TestData;
import com.identity.roles.aplication.services.DeleteRole;
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
class DeleteRoleControllerTest {
    @Mock private DeleteRole service;
    private DeleteRoleController underTest;
    private String id;

    @BeforeEach
    void setUp() {
        underTest = new DeleteRoleController(service);
        id = TestData.getRoleId().getValue();
    }

    @Test
    void delete() {
        ArgumentCaptor<String > idCaptor = ArgumentCaptor.forClass(String.class);
        underTest.delete(id);
        verify(service, atLeastOnce()).delete(idCaptor.capture());
        assertThat(idCaptor.getValue()).isEqualTo(id);
    }
}