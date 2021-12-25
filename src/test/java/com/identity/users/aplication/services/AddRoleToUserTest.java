package com.identity.users.aplication.services;

import com.identity.roles.domain.value_objects.RoleID;
import com.identity.users.domain.value_objects.AppUserID;
import com.identity.users.infrastructure.JpaUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.identity.TestData.getRoleId;
import static com.identity.TestData.getUserID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AddRoleToUserTest {
    @Mock
    private JpaUserRepository repo;
    private AddRoleToUser underTest;


    @BeforeEach
    void setUp() {
        underTest = new AddRoleToUser(repo);

    }

    @Test
    void addRole() {
        ArgumentCaptor<AppUserID> userIDArgumentCaptor = ArgumentCaptor.forClass(AppUserID.class);
        ArgumentCaptor<RoleID> roleCaptor = ArgumentCaptor.forClass(RoleID.class);

        underTest.addRole(getUserID(), getRoleId());
        verify(repo, atLeastOnce())
                .addRoleToUser(userIDArgumentCaptor.capture(), roleCaptor.capture());
        assertThat(userIDArgumentCaptor.getValue())
                .usingRecursiveComparison()
                .isEqualTo(getUserID());
        assertThat(roleCaptor.getValue())
                .usingRecursiveComparison()
                .isEqualTo(getRoleId());
    }
}