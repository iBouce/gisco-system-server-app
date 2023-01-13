package com.ibouce.giscosystemserverapp.service.user;


import com.ibouce.giscosystemserverapp.entity.user.UserModel;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {

    ResponseEntity<UserModel> saveUser(UserModel user) throws Exception;

    UserModel updateUser(UserModel user, Long id);

    void deleteUser(Long id);

    UserModel getUserById(Long id);

    UserModel getUserByName(String name);

    List<UserModel> getListUsers();


}
