package com.ibouce.giscosystemserverapp.controller.user;

import com.ibouce.giscosystemserverapp.entity.user.UserModel;
import com.ibouce.giscosystemserverapp.service.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/save")
    public ResponseEntity<UserModel> saveUser(@RequestBody UserModel user) throws Exception {
        return userService.saveUser(user);
    }

    @PutMapping("/update/{id}")
    public UserModel updateUser(@RequestBody UserModel user, @PathVariable Long id) {
        return userService.updateUser(user, id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @GetMapping("/{id}")
    public UserModel getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }


    @GetMapping("/{name}")
    public UserModel getUserByName(@RequestParam String name) {
        return userService.getUserByName(name);
    }

    @GetMapping("/all")
    public List<UserModel> getListUsers() {
        return userService.getListUsers();
    }


}
