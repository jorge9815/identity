package com.identity.users.infrastructure.controllers;

import com.identity.TestData;
import com.identity.users.aplication.AppUserDto;
import com.identity.users.aplication.services.SaveUser;
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
class SaveUserPostControllerTest {
    @Mock
    private SaveUser service;
    private SaveUserPostController underTest;
    private AppUserDto userDto;

    @BeforeEach
    void setUp() {
        underTest = new SaveUserPostController(service);
        userDto = TestData.getNewUserDto();
    }

    @Test
    void save() {
        ArgumentCaptor<AppUserDto> dtoCaptor = ArgumentCaptor.forClass(AppUserDto.class);
        underTest.save(userDto);
        verify(service, atLeastOnce()).save(dtoCaptor.capture());
        assertThat(dtoCaptor.getValue())
                .usingRecursiveComparison()
                .withStrictTypeChecking()
                .isEqualTo(userDto);
    }
}