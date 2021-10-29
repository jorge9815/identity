package com.identity.roles.infrastructure;

import com.identity.TestData;
import com.identity.exeptions.exceptions.RoleNotFound;
import com.identity.roles.domain.entity.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;
import java.util.List;

import static com.identity.TestData.getNewRole;
import static com.identity.TestData.getPaginatedListOfRoles;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@DataJpaTest
class JpaRoleRepositoryTest {
    @Autowired
    private EntityManager em;
    private JpaRoleRepository underTest;
    private List<Role> roles;

    @BeforeEach
    void setUp() {
        underTest = new JpaRoleRepository(em);
        roles = TestData.getRoles();
        roles.forEach(underTest::saveRole);
    }

    @Test
    void getById() {
        assertThat(underTest.getById(roles.get(0).getId()))
                .usingRecursiveComparison()
                .isEqualTo(roles.get(0));
    }

    @Test
    void getByName() {
        assertThat(underTest.getByName(roles.get(0).getName()))
                .usingRecursiveComparison()
                .isEqualTo(roles.get(0));
    }

    @Test
    void getRoles() {
        assertThat(underTest.getRoles(1,2))
                .usingRecursiveComparison()
        .isEqualTo(getPaginatedListOfRoles());
    }

    @Test
    void updateRoles() {
        var role = roles.get(0);
        role.setName("MY_ADMIN");
        underTest.updateRoles(role);

        assertThat(underTest.getById(role.getId()))
                .usingRecursiveComparison()
                .isEqualTo(role);
    }

    @Test
    void deleteRoles() {
        underTest.deleteRoles(roles.get(0).getId());
        assertThatExceptionOfType(RoleNotFound.class)
                .isThrownBy(()->underTest.deleteRoles(roles.get(0).getId()));
        assertThat(underTest.getRoles(1,2).getData().get(0))
                .usingRecursiveComparison()
                .isEqualTo(roles.get(1));
    }

    @Test
    void saveRole() {
        underTest.saveRole(getNewRole());
        assertThat(underTest.getRoles(1,4).getData().get(2))
                .usingRecursiveComparison()
                .isEqualTo(getNewRole());
    }
}