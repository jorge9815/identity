package com.identity.users.infrastructure.controllers;

import com.identity.users.aplication.services.DeleteUser;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class DeleteController {
    private DeleteUser deleteUser;

    public DeleteController(DeleteUser deleteUser) {
        this.deleteUser = deleteUser;
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id){
        deleteUser.delete(id);
    }
}
