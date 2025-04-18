package com.CodeLearner.HomeTenant.models.tenant.dependent;

import com.CodeLearner.HomeTenant.models.tenant.Tenant;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "Dependent")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_dependents")
public class Dependent {
    @Id
    private Long id;
    private String firstname;
    private String lastname;
    @Enumerated(EnumType.STRING)
    private RELATION relation;

    @ManyToOne(optional = false)
    @JsonIgnore
    private Tenant tenant;

    public Dependent(DependentRequest request) {
        this.id = request.getId();
        this.firstname = request.getFirstname();
        this.lastname = request.getLastname();
        this.relation = request.getRelation();
    }
}
