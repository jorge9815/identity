package com.identity.users.domain.repository;

import com.identity.roles.domain.value_objects.RoleID;
import com.identity.users.domain.entity.AppUser;
import com.identity.users.domain.value_objects.AppUserID;

import java.util.Optional;

public interface AppUserRepository {
    void saveUser(AppUser user);
    void updateUser(AppUser user);
    void deleteUser(AppUserID userID);
    AppUser getById(AppUserID userID);
    Optional<AppUser> getByUser(String user);
    AppUser getByUserAndPassword(String user, String password);
    void addRoleToUser(AppUserID userID, RoleID roleID);
}
