package com.identity.roles.aplication.services;

import com.identity.TestData;
import com.identity.roles.aplication.RoleDto;
import com.identity.roles.domain.value_objects.RoleID;
import com.identity.roles.infrastructure.JpaRoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class GetByIDTest {
    @Mock
    private JpaRoleRepository repository;
    private GetByID underTest;
    private RoleDto roleDto;
    private RoleID roleID;

    @BeforeEach
    void setUp() {
        underTest = new GetByID(repository);
        roleDto = new RoleDto(TestData.getNewRole());
        roleID = TestData.getRoleId();
    }

    @Test
    void get() {
        ArgumentCaptor<RoleID> roleCaptor = ArgumentCaptor.forClass(RoleID.class);
        given(repository.getById(roleCaptor.capture())).willReturn(TestData.getNewRole());

        var returned = underTest.get(roleID.getValue());
        assertThat(roleCaptor.getValue())
                .usingRecursiveComparison()
                .isEqualTo(roleID);
        assertThat(returned)
                .usingRecursiveComparison()
                .isEqualTo(roleDto);
    }
}