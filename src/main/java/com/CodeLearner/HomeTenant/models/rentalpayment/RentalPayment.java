package com.CodeLearner.HomeTenant.models.rentalpayment;

import com.CodeLearner.HomeTenant.models.house.House;
import com.CodeLearner.HomeTenant.models.tenant.Tenant;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_rental_payments")
public class RentalPayment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double rentAmount;
    private LocalDate paymentDate;
    private Double lateFee;
    private PAYMENTMETHOD paymentmethod;
    @ManyToOne(optional = false)
    @JsonManagedReference
    private Tenant tenant;

    @ManyToOne(optional = false)
    @JsonManagedReference
    private House house;
}
