package com.ibouce.giscosystemserverapp.entity.folder;

import com.ibouce.giscosystemserverapp.entity.user.UserModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "folder")
@AllArgsConstructor
@NoArgsConstructor
public class FolderModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long parent;
    @ManyToOne
    private UserModel user;

}
