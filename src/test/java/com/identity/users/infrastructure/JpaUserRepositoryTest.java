package com.identity.users.infrastructure;

import com.identity.TestData;
import com.identity.exeptions.exceptions.UserNotFound;
import com.identity.roles.domain.entity.Role;
import com.identity.roles.domain.value_objects.RoleID;
import com.identity.roles.infrastructure.JpaRoleRepository;
import com.identity.users.domain.entity.AppUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class JpaUserRepositoryTest {
    @Autowired
    private EntityManager em;
    private JpaUserRepository underTest;
    private JpaRoleRepository roleRepository;
    private List<Role> roles;
    private AppUser user;

    @BeforeEach
    void setUp() {
        underTest = new JpaUserRepository(em);
        roleRepository = new JpaRoleRepository(em);

        user = TestData.getUser();
        roles = TestData.getRoles();
        roles.forEach(roleRepository::saveRole);
    }

    @Test
    void saveUser() {
        underTest.saveUser(user);
        assertThat(user).usingRecursiveComparison()
                .isEqualTo(underTest.getByUser("ricardo"));
    }

    @Test
    void updateUser() {
        underTest.saveUser(user);
        user.setUser("RicardoJ");
        underTest.updateUser(user);

        assertThat(user).usingRecursiveComparison()
                .isEqualTo(underTest.getByUser("RicardoJ"));
    }

    @Test
    void deleteUser() {
        underTest.saveUser(user);
        underTest.deleteUser(user.getId());

        assertThatExceptionOfType(UserNotFound.class)
                .isThrownBy(() -> underTest.getByUser(user.getUser()));
    }

    @Test
    void getById() {
        underTest.saveUser(user);
        assertThat(underTest.getById(user.getId()))
                .usingRecursiveComparison()
                .isEqualTo(user);
    }

    @Test
    void getByUser() {
        underTest.saveUser(user);
        assertThat(underTest.getByUser(user.getUser()))
                .usingRecursiveComparison()
                .isEqualTo(user);
    }

    @Test
    void addRoleToUser() {
        Role newRole = new Role(new RoleID("472c02d6-1c61-47f0-a44f-ae50a15b1fce"), "D_ADMIN");
        underTest.saveUser(user);
        roleRepository.saveRole(newRole);

        underTest.addRoleToUser(user.getId(), newRole.getId());

        List<Role> rolesList = user.getRolesList();
        rolesList.add(newRole);
        user.setRolesList(rolesList);

        assertThat(underTest.getByUser(user.getUser()))
                .usingRecursiveComparison().isEqualTo(user);
    }
}