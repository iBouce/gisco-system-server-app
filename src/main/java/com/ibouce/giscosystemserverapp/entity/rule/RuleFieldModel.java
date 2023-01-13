/*package com.ibouce.giscosystemserverapp.entity.rule;

import com.ibouce.giscosystemserverapp.entity.document.DocumentModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "rule_field")
@AllArgsConstructor
@NoArgsConstructor
public class RuleFieldModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String value;

    @ManyToOne
    private DocumentModel document;

}*/
