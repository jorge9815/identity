package com.identity;

import com.identity.roles.domain.entity.Role;
import com.identity.roles.domain.value_objects.RoleID;
import com.identity.shared.PaginatedList;
import com.identity.shared.Password;
import com.identity.users.aplication.AppUserDto;
import com.identity.users.domain.entity.AppUser;
import com.identity.users.domain.value_objects.AppUserID;

import java.util.ArrayList;
import java.util.List;

public class TestData {
    public static List<Role> getRoles() {
        Role[] roles = {
                new Role(new RoleID("2d0b01c9-d01f-446f-b564-ba9c5589e987"), "ADMIN"),
                new Role(new RoleID("29d0ade5-cfa7-4698-9a5d-fb166bf44ab7"), "User")
        };
        return List.of(roles);
    }

    public static AppUser getUser() {
        return new AppUser(
                new AppUserID("41d33e0c-0803-40bc-ab47-f26fa89796e5"),
                "Ricardo Acosta LOLO",
                "ricardo",
                "NQ9R2wgFuZ3sL9cFBpyKREgwr7+jlSfH+56x2doD65E=",
                "NWSqVMuhjvQcPO7brI0KIeAhI2Qt8f",
                getRoles()
        );
    }

    public static AppUserDto getNewUserDto(){
        return new AppUserDto(
                "41d33e0c-0803-40bc-ab47-f26fa89796e5",
                "Ricardo Acosta LOLO",
                "ricardo",
                "Geeks@portal20",
                new ArrayList<>());
    }

    public static PaginatedList<Role> getPaginatedListOfRoles(){
        return new PaginatedList<>(2L, 1, 2, getRoles());
    }

    public static Role getNewRole(){
        return new Role(new RoleID("472c02d6-1c61-47f0-a44f-ae50a15b1fce"), "D_ADMIN");
    }

    public static RoleID getRoleId(){
        return getNewRole().getId();
    }

    public static AppUserID getUserID(){
        return getUser().getId();
    }

    public static Password getPassword(){
        return getUser().getPassword();
    }
}
