package com.app.law.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by https://github.com/kwanpham
 */
@Data
public class ImageDto {

    private String description;

    // Upload files.
    private MultipartFile[] fileDatas;

}
