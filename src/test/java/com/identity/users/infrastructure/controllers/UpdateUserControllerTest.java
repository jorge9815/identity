package com.identity.users.infrastructure.controllers;

import com.identity.TestData;
import com.identity.users.aplication.AppUserDto;
import com.identity.users.aplication.services.UpdateUser;
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
class UpdateUserControllerTest {
    @Mock
    private UpdateUser service;
    private UpdateUserController underTest;
    private AppUserDto userDto;

    @BeforeEach
    void setUp() {
        underTest = new UpdateUserController(service);
        userDto = TestData.getNewUserDto();
    }

    @Test
    void update() {
        ArgumentCaptor<AppUserDto> dtoCaptor = ArgumentCaptor.forClass(AppUserDto.class);

        underTest.update(userDto);
        verify(service, atLeastOnce()).update(dtoCaptor.capture());
        assertThat(dtoCaptor.getValue())
                .usingRecursiveComparison()
                .isEqualTo(userDto);
    }
}