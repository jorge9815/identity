package com.identity.users.infrastructure.controllers;


import com.identity.users.aplication.AppUserDto;
import com.identity.users.aplication.services.SaveUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
@Slf4j
public class SaveUserPostController {
    private final SaveUser saveUser;

    public SaveUserPostController(SaveUser saveUser) {
        this.saveUser = saveUser;
    }

    @PostMapping("save")
    public void save(@RequestBody AppUserDto userDto){
        log.info("Saving new user: {}, name: {}", userDto.getUser(), userDto.getName());
        saveUser.save(userDto);
    }
}
