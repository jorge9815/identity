package com.identity.users.aplication.services;

import com.identity.TestData;
import com.identity.users.aplication.AppUserDto;
import com.identity.users.domain.entity.AppUser;
import com.identity.users.domain.value_objects.AppUserID;
import com.identity.users.infrastructure.JpaUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class GetUserByIDTest {
    @Mock
    private JpaUserRepository repository;
    private GetUserByID underTest;
    private AppUser user;

    @BeforeEach
    void setUp() {
        underTest = new GetUserByID(repository);
        user = TestData.getUser();
    }

    @Test
    void get() {
        ArgumentCaptor<AppUserID> userIdCaptor = ArgumentCaptor.forClass(AppUserID.class);
        given(repository.getById(userIdCaptor.capture())).willReturn(user);

        var returned = underTest.get(user.getId().getValue());
        assertThat(returned).usingRecursiveComparison()
                .isEqualTo(new AppUserDto(user));
        assertThat(userIdCaptor.getValue()).usingRecursiveComparison()
                .isEqualTo(user.getId());
    }
}