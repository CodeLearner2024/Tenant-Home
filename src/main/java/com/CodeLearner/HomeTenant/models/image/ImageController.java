package com.CodeLearner.HomeTenant.models.image;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author ND. Eric
 */
@RestController
@RequiredArgsConstructor
public class ImageController {

    private final ImageService profileServiceImpl;



    @PostMapping("/profile/upload")
    public ResponseEntity<Object> uploadImage(@Valid @RequestParam Long rentalPaymentId, @RequestParam MultipartFile file, HttpServletRequest request) {
        ImageOperationResponse response = this.profileServiceImpl.uploadProfile(rentalPaymentId, file, request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping(path = "/profile/{id}")
    public ResponseEntity<Resource> findProfile(@PathVariable("id") Long id, HttpServletRequest request) {
        ImageResponse response = this.profileServiceImpl.findProfileById(id);
        String img = "";
        try {
            img = request.getServletContext().getMimeType(response.getProfileName());
        } catch (Exception e) {
            img = MediaType.APPLICATION_OCTET_STREAM_VALUE;
        }
        Resource profileresource = response.getImage();

        return ResponseEntity.ok().contentType(MediaType.parseMediaType(img)).header(HttpHeaders.CONTENT_DISPOSITION, "inline;fileName=" + response
                .getProfileName()).body(profileresource);


    }


}
