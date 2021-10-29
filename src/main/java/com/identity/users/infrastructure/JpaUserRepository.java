package com.identity.users.infrastructure;

import com.identity.exeptions.exceptions.UserNotFound;
import com.identity.roles.domain.value_objects.RoleID;
import com.identity.roles.infrastructure.RoleModel;
import com.identity.users.domain.entity.AppUser;
import com.identity.users.domain.repository.AppUserRepository;
import com.identity.users.domain.value_objects.AppUserID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.Optional;

@Repository
@Transactional
@Slf4j
public class JpaUserRepository implements AppUserRepository {
    private final EntityManager em;

    public JpaUserRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public void saveUser(AppUser user) {
        log.info("Saving user information in the database");
        em.persist(new AppUserModel(user));
    }

    @Override
    public void updateUser(AppUser user) {
        var model = getModelById(user.getId().getValue());
        model.update(user);
    }

    @Override
    public void deleteUser(AppUserID userID) {
        em.remove(getModelById(userID.getValue()));
    }

    @Override
    public AppUser getById(AppUserID userID) {
        return getModelById(userID.getValue()).toAppUser();
    }

    @Override
    public AppUser getByUser(String user) throws UserNotFound {
        return getModelByUser(user).toAppUser();
    }

    @Override
    public void addRoleToUser(AppUserID userID, RoleID roleID) {
        var user = getModelById(userID.getValue());
        user.addRole(getRoleModel(roleID.getValue()));
    }

    private AppUserModel getModelById(String id) {
        return em.createQuery("FROM AppUserModel u WHERE u.id=:id", AppUserModel.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    private AppUserModel getModelByUser(String user) {
        try {
            return em.createQuery("FROM AppUserModel u WHERE u.user =: user", AppUserModel.class)
                    .setParameter("user", user)
                    .getSingleResult();
        } catch (NoResultException noResultException) {
            log.error("Username: {} does not exist", user);
            throw new UserNotFound();
        }
    }

    private RoleModel getRoleModel(String roleID) {
        return em.createQuery("FROM RoleModel r WHERE r.id=:id", RoleModel.class)
                .setParameter("id", roleID)
                .getSingleResult();
    }

}
