package com.app.law.service;

import com.app.law.dto.ImageDto;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by https://github.com/kwanpham
 */
public interface ImageService {

    boolean uploadImage(HttpServletRequest request, ImageDto imageDto);

    byte[] getImage(String imageName) throws IOException;

}
