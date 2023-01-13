package com.ibouce.giscosystemserverapp.entity.document;

import com.ibouce.giscosystemserverapp.entity.folder.FolderModel;
import com.ibouce.giscosystemserverapp.entity.user.UserModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "document")
@AllArgsConstructor
@NoArgsConstructor
public class DocumentModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @ManyToOne
    private UserModel user;

    @ManyToOne
    private FolderModel folder;

    /*@ManyToOne
    private RuleModel rule;*/

    /*@ManyToOne
    private RuleFieldModel ruleField;*/

}
