package com.ibouce.giscosystemserverapp.entity.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ibouce.giscosystemserverapp.entity.folder.FolderModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
public class UserModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;
    private String role = "ADMIN";
    private boolean enabled = true;

    @OneToMany(mappedBy = "user")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<FolderModel> folders;

}
