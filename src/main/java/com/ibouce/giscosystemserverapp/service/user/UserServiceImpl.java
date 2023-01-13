package com.ibouce.giscosystemserverapp.service.user;

import com.ibouce.giscosystemserverapp.entity.folder.FolderModel;
import com.ibouce.giscosystemserverapp.entity.user.UserModel;
import com.ibouce.giscosystemserverapp.repository.folder.FolderRepository;
import com.ibouce.giscosystemserverapp.repository.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    FolderRepository folderRepository;

    @Value("${directory.root}")
    String root;

    @Override
    public ResponseEntity<UserModel> saveUser(UserModel user) throws Exception {
        UserModel currentUser = this.userRepository.findByUsername(user.getUsername());
        if (currentUser != null) {
            throw new Exception("User is already present !");
        } else {
            System.out.println("Create User !");
            currentUser = this.userRepository.save(user);
            System.out.println("Create Folder !");
            folderRepository.save(new FolderModel(null, currentUser.getUsername(), null, currentUser));
            System.out.println("Create Root Folder in File System! " + root + "/" + currentUser.getUsername());
            File file = new File(root + "/" + currentUser.getUsername() + "/");
            file.mkdirs();
        }
        return ResponseEntity.ok().body(currentUser);
    }

    @Override
    public UserModel updateUser(UserModel user, Long id) {
        user.setId(id);
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        UserModel user = userRepository.findById(id).get();
        userRepository.delete(user);
    }

    @Override
    public UserModel getUserById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public UserModel getUserByName(String name) {
        return userRepository.findByUsername(name);
    }

    @Override
    public List<UserModel> getListUsers() {
        return userRepository.findAll();
    }

}
