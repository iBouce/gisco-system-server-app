/*package com.ibouce.giscosystemserverapp.entity.rule;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "rule_property")
@AllArgsConstructor
@NoArgsConstructor
public class RulePropertyModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String type;

    @ManyToOne
    private RuleModel rule;

}
*/