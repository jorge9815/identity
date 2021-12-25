package com.identity.users.aplication.services;

import com.identity.TestData;
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
class GetByUserTest {
    @Mock
    private JpaUserRepository repository;
    private GetByUser underTest;
    private String user;

    @BeforeEach
    void setUp() {
        underTest = new GetByUser(repository);
        user = TestData.getUser().getUser();
    }

    @Test
    void get() {
        ArgumentCaptor<String> userCaptor = ArgumentCaptor.forClass(String.class);
        given(repository.getByUser(userCaptor.capture())).willReturn(TestData.getUser());

        underTest.get(user);
        assertThat(userCaptor.getValue()).isEqualTo(user);
    }
}