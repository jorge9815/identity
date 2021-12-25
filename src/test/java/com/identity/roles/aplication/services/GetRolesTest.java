package com.identity.roles.aplication.services;

import com.identity.TestData;
import com.identity.roles.aplication.RoleDto;
import com.identity.roles.infrastructure.JpaRoleRepository;
import com.identity.shared.PaginatedList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class GetRolesTest {
    @Mock private JpaRoleRepository repository;
    private GetRoles underTest;
    private PaginatedList<RoleDto> roleDtoPaginatedList;

    @BeforeEach
    void setUp() {
        underTest = new GetRoles(repository);
        roleDtoPaginatedList = TestData.getPaginatedListOfRoleDto();
    }

    @Test
    void get() {
        ArgumentCaptor<Integer> pageCaptor = ArgumentCaptor.forClass(Integer.class);
        ArgumentCaptor<Integer> perPageCaptor = ArgumentCaptor.forClass(Integer.class);
        given(repository.getRoles(pageCaptor.capture(), perPageCaptor.capture()))
                .willReturn(TestData.getPaginatedListOfRoles());

        var returned = underTest
                .get(roleDtoPaginatedList.getPage(), roleDtoPaginatedList.getPerPage());
        assertThat(returned)
                .usingRecursiveComparison()
                .withStrictTypeChecking()
                .isEqualTo(roleDtoPaginatedList);
        assertThat(pageCaptor.getValue())
                .isEqualTo(roleDtoPaginatedList.getPage());
        assertThat(perPageCaptor.getValue())
                .isEqualTo(roleDtoPaginatedList.getPerPage());

    }
}