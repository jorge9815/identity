package com.identity.users.infrastructure.controllers;

import com.identity.TestData;
import com.identity.roles.domain.value_objects.RoleID;
import com.identity.users.aplication.services.AddRoleToUser;
import com.identity.users.domain.value_objects.AppUserID;
import com.identity.users.infrastructure.JpaUserRepository;
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
class AddRoleToUserPostControllerTest {
    @Mock
    private AddRoleToUser service;
    private AddRoleToUserPostController underTest;
    private RoleID roleID;
    private AppUserID userID;

    @BeforeEach
    void setUp() {
        underTest = new AddRoleToUserPostController(service);
        roleID = TestData.getRoleId();
        userID = TestData.getUserID();
    }

    @Test
    void addRole() {
        ArgumentCaptor<AppUserID> userIdCaptor = ArgumentCaptor.forClass(AppUserID.class);
        ArgumentCaptor<RoleID> roleIdCaptor = ArgumentCaptor.forClass(RoleID.class);

        underTest.addRole(userID.getValue(), roleID.getValue());
        verify(service, atLeastOnce()).addRole(userIdCaptor.capture(), roleIdCaptor.capture());
        assertThat(userIdCaptor.getValue())
                .usingRecursiveComparison()
                .isEqualTo(userID);
        assertThat(roleIdCaptor.getValue())
                .usingRecursiveComparison()
                .isEqualTo(roleID);
    }
}