package com.identity.users.aplication.services;

import com.identity.TestData;
import com.identity.users.aplication.AppUserDto;
import com.identity.users.domain.entity.AppUser;
import com.identity.users.infrastructure.JpaUserRepository;
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
class SaveUserTest {
    @Mock
    private JpaUserRepository repository;
    private  SaveUser underTest;
    private AppUserDto userDto;

    @BeforeEach
    void setUp() {
        underTest = new SaveUser(repository);
        userDto = TestData.getNewUserDto();
    }

    @Test
    void save() {
        ArgumentCaptor<AppUser> userCaptor = ArgumentCaptor.forClass(AppUser.class);

        underTest.save(userDto);
        verify(repository, atLeastOnce()).saveUser(userCaptor.capture());

        assertThat(userCaptor.getValue())
                .usingRecursiveComparison()
                .ignoringFields("password")
                .ignoringFields("salt")
                .isEqualTo(userDto.toAppUser());
    }
}