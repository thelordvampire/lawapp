package com.app.law.service;

import com.app.law.dto.ImageDto;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by https://github.com/kwanpham
 */
public interface ImageService {

    boolean uploadImage(HttpServletRequest request, ImageDto imageDto);
    String uploadImage(MultipartFile imageFile);

    byte[] getImage(String imageName) throws IOException;

}
