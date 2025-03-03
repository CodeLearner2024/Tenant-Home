package com.CodeLearner.HomeTenant.models.image;


import com.CodeLearner.HomeTenant.models.rentalpayment.RentalPayment;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity(name = "Image")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_files")
public class Image {
    @Id
    private Long id;
    @Column(columnDefinition = "TEXT")
    private String profileName;
    @Lob
    @Column(nullable = false, columnDefinition = "LONGTEXT")
    private String image;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JsonBackReference
    private RentalPayment rentalPayment;

    public Image(Long id, String profileName, String image) {
        this.id = id;
        this.profileName = profileName;
        this.image = image;
    }
}
