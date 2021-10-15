package com.identity.users.domain.repository;

import com.identity.users.domain.entity.AppUser;
import com.identity.users.domain.value_objects.AppUserID;

public interface AppUserRepository {
    void saveUser(AppUser user);
    void updateUser(AppUser user);
    void deleteUser(AppUserID userID);
    AppUser getById(AppUserID userID);
    AppUser getByUser(String user);
    AppUser getByUserAndPassword(String user, String password);
}
