package com.CodeLearner.HomeTenant.models.image;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author ND. Eric
 */
public interface ImageService {

    public ImageOperationResponse uploadProfile(Long idEmployee, MultipartFile multipartFile,
                                                  HttpServletRequest request);

    public ImageResponse findProfileById(Long id);

}
