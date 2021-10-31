package com.identity.users.aplication.services;

import com.identity.TestData;
import com.identity.exeptions.exceptions.WrongPassword;
import com.identity.shared.Password;
import com.identity.users.aplication.AppUserDto;
import com.identity.users.domain.entity.AppUser;
import com.identity.users.infrastructure.JpaUserRepository;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.identity.TestData.getPassword;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class GetByUserAndPasswordTest {
    @Mock
    private JpaUserRepository repository;
    private GetByUserAndPassword underTest;
    private AppUser user;

    @BeforeEach
    void setUp() {
        underTest = new GetByUserAndPassword(repository);
        user = TestData.getUser();
    }


    private ArgumentCaptor<String> getUserNameCaptor() {
        ArgumentCaptor<String> userNameCaptor= ArgumentCaptor.forClass(String.class);
        given(repository.getByUser(userNameCaptor.capture())).willReturn(user);
        return userNameCaptor;
    }

//    @Test
//    void getCorrectPassword() {
//        ArgumentCaptore<String> userNameCaptor = getUserNameCaptor();
//
//        var returned = underTest.get(user.getUser(), "Geeks@portal20");
//
//        AssertionsForClassTypes.assertThat(userNameCaptor.getValue()).isEqualTo(user.getUser());
//        assertThat(returned)user
//                .isEqualTo(new AppUserDto(user));
//    }

    @Test
    void withWrongPassword(){
        ArgumentCaptor<String> username = getUserNameCaptor();
        assertThatExceptionOfType(WrongPassword.class)
                .isThrownBy(()-> underTest.get(user.getUser(), "muka"));
    }
}