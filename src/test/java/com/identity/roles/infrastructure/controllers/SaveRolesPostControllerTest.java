package com.identity.roles.infrastructure.controllers;

import com.identity.TestData;
import com.identity.roles.aplication.RoleDto;
import com.identity.roles.aplication.services.SaveRole;
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
class SaveRolesPostControllerTest {
    @Mock private SaveRole service;
    private SaveRolesPostController underTest;
    private RoleDto dto;

    @BeforeEach
    void setUp() {
        underTest = new SaveRolesPostController(service);
        dto = new RoleDto(TestData.getNewRole());

    }

    @Test
    void save() {
        ArgumentCaptor<RoleDto> dtoCaptor = ArgumentCaptor.forClass(RoleDto.class);
        underTest.save(dto);
        verify(service, atLeastOnce()).save(dtoCaptor.capture());
        assertThat(dtoCaptor.getValue())
                .usingRecursiveComparison()
                .isEqualTo(dto);
    }
}