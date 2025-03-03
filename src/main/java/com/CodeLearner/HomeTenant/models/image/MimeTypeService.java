/**
 *
 */
package com.CodeLearner.HomeTenant.models.image;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;


@Service
public class MimeTypeService {

    public String getMimeType(HttpServletRequest request, MultipartFile multipartFile) {
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        String mimeType = null;
        try {
            mimeType = request.getServletContext().getMimeType(fileName);
        } catch (Exception e) {
            throw new UnsupportedOperationException("could not determine the proper mimetype for");
        }
        return mimeType;
    }
}
