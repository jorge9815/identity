package com.identity.roles.infrastructure.controllers;

import com.identity.TestData;
import com.identity.roles.aplication.RoleDto;
import com.identity.roles.aplication.services.UpdateRole;
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
class UpdateRolePutControllerTest {
    @Mock
    private UpdateRole service;
    private UpdateRolePutController underTest;
    private RoleDto dto;

    @BeforeEach
    void setUp() {
        underTest = new UpdateRolePutController(service);
        dto = new RoleDto(TestData.getNewRole());
    }

    @Test
    void update() {
        ArgumentCaptor<RoleDto> dtoCaptor = ArgumentCaptor.forClass(RoleDto.class);
        underTest.update(dto);
        verify(service, atLeastOnce()).update(dtoCaptor.capture());
        assertThat(dtoCaptor.getValue())
                .usingRecursiveComparison()
                .isEqualTo(dto);
    }
}