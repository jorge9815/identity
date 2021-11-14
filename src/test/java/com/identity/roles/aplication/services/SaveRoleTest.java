package com.identity.roles.aplication.services;

import com.identity.TestData;
import com.identity.roles.aplication.RoleDto;
import com.identity.roles.domain.entity.Role;
import com.identity.roles.infrastructure.JpaRoleRepository;
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
class SaveRoleTest {
    @Mock
    private JpaRoleRepository repository;
    private SaveRole underTest;
    private RoleDto dto;

    @BeforeEach
    void setUp() {
        underTest = new SaveRole(repository);
        dto = new RoleDto(TestData.getNewRole());
    }

    @Test
    void save() {
        ArgumentCaptor<Role> roleCaptor = ArgumentCaptor.forClass(Role.class);
        underTest.save(dto);
        verify(repository, atLeastOnce()).saveRole(roleCaptor.capture());
        assertThat(roleCaptor.getValue())
                .usingRecursiveComparison()
                .isEqualTo(dto.toRole());
    }
}