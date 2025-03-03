package com.CodeLearner.HomeTenant.models.image;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.core.io.Resource;

/**
 * @author ND. Eric
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageResponse {
    private String profileName;
    private Resource image;
    private Long rentalPaymentId;

    public ImageResponse(String profileName, Resource image) {
        this.profileName = profileName;
        this.image = image;
    }
}
