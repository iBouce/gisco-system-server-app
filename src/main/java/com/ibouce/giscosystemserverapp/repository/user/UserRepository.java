package com.ibouce.giscosystemserverapp.repository.user;

import com.ibouce.giscosystemserverapp.entity.user.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {

    UserModel findByUsername(String username);

    UserModel findByUsernameAndPassword(String username, String password);

    Boolean existsByUsername(String username);

}
