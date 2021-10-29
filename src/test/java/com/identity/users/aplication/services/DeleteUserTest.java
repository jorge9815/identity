package com.identity.users.aplication.services;

import com.identity.TestData;
import com.identity.users.domain.value_objects.AppUserID;
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
class DeleteUserTest {
    @Mock
    private JpaUserRepository repo;
    private DeleteUser underTest;
    private AppUserID id;

    @BeforeEach
    void setUp() {
        underTest = new DeleteUser(repo);
        id = TestData.getUserID();
    }

    @Test
    void delete() {
        ArgumentCaptor<AppUserID> idCaptor = ArgumentCaptor.forClass(AppUserID.class);

        underTest.delete(id.getValue());
        verify(repo, atLeastOnce()).deleteUser(idCaptor.capture());
        assertThat(idCaptor.getValue())
                .usingRecursiveComparison()
                .isEqualTo(id);
    }
}