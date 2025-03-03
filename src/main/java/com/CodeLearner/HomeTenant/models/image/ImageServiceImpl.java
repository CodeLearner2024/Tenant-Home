package com.CodeLearner.HomeTenant.models.image;

import com.CodeLearner.HomeTenant.models.rentalpayment.RentalPayment;
import com.CodeLearner.HomeTenant.models.rentalpayment.RentalPaymentRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.Base64;
import java.util.Optional;

/**
 * @author ND. Eric
 */
@Service
@Transactional
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageRepository profileRepository;
    private final RentalPaymentRepository rentalPaymentRepository;
    private final MimeTypeService mimeTypeService;





    @Override
    public ImageOperationResponse uploadProfile(@NotNull(message = "provide employee who is going to hold this profile picture") Long rentalPayementId, MultipartFile multipartFile, HttpServletRequest request) {

        if (rentalPayementId == null) {
            throw new UnsupportedOperationException("provide employee who is going to hold this profile picture");
        }
        RentalPayment rentalPayment = this.rentalPaymentRepository.findById(rentalPayementId)
                .orElseThrow(() -> new UnsupportedOperationException("choose the existing rental-payment"));
        Optional<Image> potentialProfile = this.profileRepository.lookUpByEmployee(rentalPayment.getId());

        Image profile = null;
        String mimeType = this.mimeTypeService.getMimeType(request, multipartFile);
        if (!mimeType.startsWith("image/")) {
            throw new UnsupportedOperationException("file type not supported for profile images");
        }

        try {
            String generatedValue = RandomStringUtils.randomNumeric(1);
            profile = new Image(Long.valueOf(generatedValue), multipartFile.getOriginalFilename(), Base64.getEncoder().encodeToString(multipartFile.getBytes()));
        } catch (IOException e) {
            throw new IllegalArgumentException("Error reading file");
        }

        if (potentialProfile.isPresent()) {
            profile.setId(potentialProfile.get().getId());
        }
        profile.setRentalPayment(rentalPayment);
        this.profileRepository.save(profile);
        String url = ServletUriComponentsBuilder.fromCurrentRequest().toUriString().replaceFirst("upload", "");
        String urlProfile = url;
        return new ImageOperationResponse("upload", ImageOperationResponse.successResponse(), urlProfile);
    }

    @Override
    public ImageResponse findProfileById(Long id) {
        Optional<Image> profileById = this.profileRepository.findById(id);
        if (profileById.isEmpty()) {
            throw new UnsupportedOperationException("Does not exist");
        }
        Image profile = profileById.get();
        Resource resource = new ByteArrayResource(profile.getImage().getBytes());
        return new ImageResponse(profile.getProfileName(), resource);
    }


}
