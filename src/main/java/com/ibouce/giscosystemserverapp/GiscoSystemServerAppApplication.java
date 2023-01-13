package com.ibouce.giscosystemserverapp;

import com.ibouce.giscosystemserverapp.entity.user.UserModel;
import com.ibouce.giscosystemserverapp.service.document.DocumentService;
import com.ibouce.giscosystemserverapp.service.folder.FolderService;
import com.ibouce.giscosystemserverapp.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

@SpringBootApplication
public class GiscoSystemServerAppApplication {//implements CommandLineRunner

    @Value("${directory.root}")
    String root;

    @Autowired
    private UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(GiscoSystemServerAppApplication.class, args);
    }


    @Bean
    CommandLineRunner run(UserService userService, FolderService folderService, DocumentService documentService) {
        return args -> {
            // Create Root Folder
            File file = new File(root);
            file.mkdirs();

            //User *********************************
            //UserModel user1 =
            userService.saveUser(new UserModel(null, "admin", "admin", "ADMIN", true, new ArrayList<>()));
            //UserModel user2 =
            userService.saveUser(new UserModel(null, "user", "user", " USER", true, new ArrayList<>()));

            //Folder ******************************

            // folder - user
            // - admin
            //  - Facture
            //    - Year
            //      - Month
            //  - BC
            //    - Year
            //      - Month

            /*folderService.saveFolderToUser(new FolderModel(null, user1.getUsername(), null, user1));
            folderService.saveFolderToUser(new FolderModel(10L, "Facture", user1.getId(), user1));
            folderService.saveFolderToUser(new FolderModel(11L, "Year", 10L, user1));
            FolderModel folderMonth = folderService.saveFolderToUser(new FolderModel(12L, "Month", 11L, user1));
            folderService.saveFolderToUser(new FolderModel(13L, "BC", user1.getId(), user1));
            folderService.saveFolderToUser(new FolderModel(14L, "Year", 13L, user1));
            folderService.saveFolderToUser(new FolderModel(15L, "Month", 14L, user1));*/

            // - user
            //  - Test

            /*folderService.saveFolderToUser(new FolderModel(null, user2.getUsername(), null, user2));
            FolderModel folderTest = folderService.saveFolderToUser(new FolderModel(20L, "Test", user2.getId(), user2));*/

            //Rule ******************************

            // rule - rule_property
            // - Facture
            //   - Bill, int
            //   - Date, date
            //   - Provider, varchar

            // rule_field - document
            //  - Bill, 10
            //  - Date, 01/01/2023

            //Document **************************

            // document - user - folder - rule - rule_field
            // - facture01.pdf
            // - facture02.pdf

            /*documentService.saveDocumentToFolder(new DocumentModel(null, "facture01.pdf", user1, folderMonth));
            documentService.saveDocumentToFolder(new DocumentModel(null, "facture02.pdf", user2, folderTest));*/

        };
    }


    /*@Override
    public void run(String... args) throws Exception {
        UserModel adminUser = new UserModel();
        adminUser.setUsername("admin");
        adminUser.setPassword("admin");

        //UserModel userUser = new UserModel();
        //userUser.setUsername("user");
        //userUser.setPassword("user");

        RoleModel adminRole = new RoleModel();
        adminRole.setId(null);
        adminRole.setName("ADMIN");

        //RoleModel userRole = new RoleModel();
        //userRole.setId(null);
        //userRole.setName("USER");

        Set<UserRoleModel> userRoleSet = new HashSet<>();
        UserRoleModel userRoleModel1 = new UserRoleModel();
        userRoleModel1.setUser(adminUser);
        userRoleModel1.setRole(adminRole);

        userRoleSet.add(userRoleModel1);

        //UserRoleModel userRoleModel2 = new UserRoleModel();
        //userRoleModel2.setUser(userUser);
        //userRoleModel2.setRole(userRole);

        //userRoleSet.add(userRoleModel2);

        UserModel admin = this.userService.saveUser(adminUser, userRoleSet);
        System.out.println("Admin " + admin.getUsername() + " Created !");

        //UserModel user = this.userService.saveUser(userUser, userRoleSet);
        //System.out.println("User " + user.getUsername() + " Created !");
    }*/

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
        corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
                "Accept", "Authorization", "Origin, Accept", "X-Requested-With",
                "Access-Control-Request-Method", "Access-Control-Request-Headers"));
        corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Authorization",
                "Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));
        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(urlBasedCorsConfigurationSource);
    }

}
