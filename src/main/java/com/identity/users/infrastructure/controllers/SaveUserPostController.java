package com.identity.users.infrastructure.controllers;


import com.identity.users.aplication.AppUserDto;
import com.identity.users.aplication.services.SaveUser;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class SaveUserPostController {
    private SaveUser saveUser;

    public SaveUserPostController(SaveUser saveUser) {
        this.saveUser = saveUser;
    }

    @PostMapping("save")
    public void save(@RequestBody AppUserDto userDto){
        saveUser.save(userDto);
    }
}
