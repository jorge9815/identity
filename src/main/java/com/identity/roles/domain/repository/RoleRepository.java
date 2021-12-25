package com.identity.roles.domain.repository;

import com.identity.roles.domain.entity.Role;
import com.identity.roles.domain.value_objects.RoleID;
import com.identity.shared.PaginatedList;

public interface RoleRepository {
    Role getById(RoleID id);
    Role getByName(String name);
    PaginatedList<Role> getRoles(int page, int perPage);
    void updateRoles(Role role);
    void deleteRoles(RoleID roleID);
    void saveRole(Role role);
}
