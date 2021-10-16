package com.identity.users.infrastructure;

import com.identity.roles.domain.value_objects.RoleID;
import com.identity.roles.infrastructure.RoleModel;
import com.identity.users.domain.entity.AppUser;
import com.identity.users.domain.repository.AppUserRepository;
import com.identity.users.domain.value_objects.AppUserID;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Repository
@Transactional
public class JpaUserRepository implements AppUserRepository {
    private EntityManager em;

    public JpaUserRepository(EntityManager em) {
        this.em = em;
    }


    @Override
    public void saveUser(AppUser user) {
        em.persist(new AppUserModel(user));
    }

    @Override
    public void updateUser(AppUser user) {
        var model = getModelById(user.getId().getValue());
        model.update(user);//todo comprobar q update por transactional
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
    public AppUser getByUser(String user) {
        return em.createQuery("FROM AppUserModel u WHERE u.user =: user", AppUserModel.class)
                .setParameter("user", user)
                .getSingleResult()
                .toAppUser();
    }

    @Override
    public AppUser getByUserAndPassword(String user, String password) {
        return em
                .createQuery("FROM AppUserModel u WHERE u.user =: user AND u.password =: password", AppUserModel.class)
                .setParameter("user", user)
                .setParameter("password",password)
                .getSingleResult()
                .toAppUser();
    }

    @Override
    public void addRoleToUser(AppUserID userID, RoleID roleID) {
        var role = em.createQuery("FROM RoleModel r WHERE r.id=:id", RoleModel.class)
                .setParameter("id", roleID.getValue())
                .getSingleResult();

        var user = getModelById(userID.getValue());
        user.addRole(role);//todo chek for transactional
    }

    private AppUserModel getModelById(String id) {
        return em.createQuery("FROM AppUserModel u WHERE u.id=:id", AppUserModel.class)
                .setParameter("id", id)
                .getSingleResult();
    }
}
