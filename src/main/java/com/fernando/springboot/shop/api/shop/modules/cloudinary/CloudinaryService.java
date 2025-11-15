package com.fernando.springboot.shop.api.shop.modules.cloudinary;

import java.io.IOException;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CloudinaryService {
    private final Cloudinary cloudinary;

    public Map<String, String> uploadImage(MultipartFile file, String folder) throws IOException {
        Map<?, ?> uploadResult = cloudinary.uploader()
            .upload(
                file.getBytes(), 
                ObjectUtils.asMap(
                    "folder", folder,
                    "resource_type", "image"
                )
        );

        return Map.of(
            "url", uploadResult.get("secure_url").toString(),
            "publicId", uploadResult.get("public_id").toString()
        );
    }
}
