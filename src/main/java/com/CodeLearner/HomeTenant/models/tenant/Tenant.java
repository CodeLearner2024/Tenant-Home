package com.CodeLearner.HomeTenant.models.tenant;

import com.CodeLearner.HomeTenant.models.leaseagreement.LeaseAgreement;
import com.CodeLearner.HomeTenant.models.tenant.dependent.Dependent;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_tenants")
public class Tenant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "firstname must be provided")
    private String firstname;
    @NotBlank(message = "lastname must be provided")
    private String lastname;
    @NotBlank(message = "identity number must be provided")
    private String identityNumber;
    private String email;
    @NotBlank(message = "phone number must be provided")
    private String phoneNumber;

    @OneToMany(mappedBy = "tenant")
    private List<Dependent> dependents;

    @OneToMany(mappedBy = "tenant")
    @JsonManagedReference
    private List<LeaseAgreement> leaseAgreements;
}
