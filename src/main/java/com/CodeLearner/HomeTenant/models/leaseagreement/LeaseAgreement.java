package com.CodeLearner.HomeTenant.models.leaseagreement;

import com.CodeLearner.HomeTenant.models.house.House;
import com.CodeLearner.HomeTenant.models.tenant.Tenant;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity(name = "LeaseAgreement")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_lease_agreements")
public class LeaseAgreement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate agreementDate;
    private LocalDate rentPayementDueDate;
    private Double modifiedRent;

    @ManyToOne(optional = false)
    @JsonBackReference
    private Tenant tenant;

    @ManyToOne(optional = false)
    @JsonBackReference
    private House house;
}
