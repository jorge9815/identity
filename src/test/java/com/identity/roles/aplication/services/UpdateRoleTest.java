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
class UpdateRoleTest {
    @Mock
    private JpaRoleRepository repository;
    private UpdateRole underTest;
    private Role role;

    @BeforeEach
    void setUp() {
        underTest = new UpdateRole(repository);
        role = TestData.getNewRole();
    }

    @Test
    void update() {
        ArgumentCaptor<Role> roleCaptor = ArgumentCaptor.forClass(Role.class);
        underTest.update(new RoleDto(role));
        verify(repository, atLeastOnce()).updateRoles(roleCaptor.capture());
        assertThat(roleCaptor.getValue())
                .usingRecursiveComparison()
                .isEqualTo(role);
    }
}