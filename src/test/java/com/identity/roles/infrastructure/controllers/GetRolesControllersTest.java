package com.identity.roles.infrastructure.controllers;

import com.identity.roles.aplication.services.GetRoles;
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
class GetRolesControllersTest {
    @Mock
    private GetRoles service;
    private GetRolesControllers underTest;
    private final int perPage = 3;
    private final int page = 2;

    @BeforeEach
    void setUp() {
        underTest = new GetRolesControllers(service);
    }

    @Test
    void getRoles() {
        ArgumentCaptor<Integer> pageCaptor = ArgumentCaptor.forClass(Integer.class);
        ArgumentCaptor<Integer> perPageCaptor = ArgumentCaptor.forClass(Integer.class);
        underTest.getRoles(page, perPage);
        verify(service, atLeastOnce()).get(pageCaptor.capture(), perPageCaptor.capture());
        assertThat(pageCaptor.getValue()).isEqualTo(page);
        assertThat(perPageCaptor.getValue()).isEqualTo(perPage);
    }
}