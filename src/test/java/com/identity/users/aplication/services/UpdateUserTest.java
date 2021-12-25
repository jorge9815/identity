package com.identity.users.aplication.services;

import com.identity.TestData;
import com.identity.roles.aplication.RoleDto;
import com.identity.users.aplication.AppUserDto;
import com.identity.users.domain.entity.AppUser;
import com.identity.users.infrastructure.JpaUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UpdateUserTest {
    @Mock
    private JpaUserRepository repository;
    private UpdateUser underTest;
    private AppUser user;

    @BeforeEach
    void setUp() {
        underTest = new UpdateUser(repository);
        user = TestData.getUser();
    }

    @Test
    void update() {
        ArgumentCaptor<AppUser> userCaptor = ArgumentCaptor.forClass(AppUser.class);
        AppUserDto newDto = TestData.getNewUserDto();
        newDto.setRolesList(user.getRolesList().stream()
                .map(RoleDto::new).collect(Collectors.toList()));

        underTest.update(newDto);
        verify(repository, atLeastOnce()).updateUser(userCaptor.capture());

        assertThat(userCaptor.getValue()).usingRecursiveComparison()
                .ignoringFields("password")
                .ignoringFields("salt")
                .isEqualTo(newDto.toAppUser());
        assertThat(userCaptor.getValue().getPassword())
                .usingRecursiveComparison()
                .isNotEqualTo(user.getPassword());
    }
}