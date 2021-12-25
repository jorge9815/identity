package com.identity.roles.aplication.services;

import com.identity.TestData;
import com.identity.roles.aplication.RoleDto;
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
class GetByNameTest {
    @Mock
    private JpaRoleRepository repository;
    private GetByName underTest;
    private String name;
    private RoleDto dto;

    @BeforeEach
    void setUp() {
        underTest = new GetByName(repository);
        name = TestData.getRoleId().getValue();
        dto = new RoleDto(TestData.getNewRole());
    }

    @Test
    void get() {
        ArgumentCaptor<String> nameCaptor = ArgumentCaptor.forClass(String.class);
        given(repository.getByName(nameCaptor.capture())).willReturn(TestData.getNewRole());
        var returned = underTest.get(name);
        assertThat(nameCaptor.getValue()).isEqualTo(name);
        assertThat(returned)
                .usingRecursiveComparison()
                .isEqualTo(dto);
    }
}