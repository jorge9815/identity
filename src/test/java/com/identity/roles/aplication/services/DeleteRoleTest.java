package com.identity.roles.aplication.services;

import com.identity.TestData;
import com.identity.roles.domain.value_objects.RoleID;
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
class DeleteRoleTest {
    @Mock
    private JpaRoleRepository repository;
    private DeleteRole underTest;
    private String id;

    @BeforeEach
    void setUp() {
        underTest =new DeleteRole(repository);
        id = TestData.getRoleId().getValue();
    }

    @Test
    void delete() {
        ArgumentCaptor<RoleID> idCaptor = ArgumentCaptor.forClass(RoleID.class);
        underTest.delete(id);
        verify(repository, atLeastOnce()).deleteRoles(idCaptor.capture());
        assertThat(idCaptor.getValue().getValue()).isEqualTo(id);
    }
}