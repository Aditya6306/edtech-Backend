package com.learnify_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.Map;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import com.cloudinary.Cloudinary;

@Service
public class UploadToCloudinary {
    @Autowired
    private Cloudinary cloudinary;

    public String uploadFile(MultipartFile file) {
        try {
            Map uploadResult = cloudinary.uploader().upload(
                    file.getBytes(),
                    ObjectUtils.emptyMap());

            return uploadResult.get("url").toString();

        } catch (IOException e) {
            throw new RuntimeException("File upload failed");
        }
    }
}
