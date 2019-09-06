package com.app.law.controller;

import com.app.law.dto.ImageDto;
import com.app.law.service.ImageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;
import org.synchronoss.cloud.nio.multipart.Multipart;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * Created by https://github.com/kwanpham
 */

@RestController
public class ImageController {

    private static final Logger logger = LoggerFactory.getLogger(ImageController.class);

    @Autowired
    private ImageService imageService;

    // POST: Sử lý Upload
    @RequestMapping(value = "/uploadOneFile", method = RequestMethod.POST)
    public ResponseEntity uploadOneFileHandlerPOST(HttpServletRequest request) {
        logger.info("upload one file");

        StandardMultipartHttpServletRequest request1 = (StandardMultipartHttpServletRequest) request;
        MultiValueMap<String, MultipartFile> multiFileMap = request1.getMultiFileMap();
        MultipartFile imageFile = multiFileMap.getFirst("file");
        String imagePath = imageService.uploadImage( imageFile);

        return ResponseEntity.status(imagePath!= null? HttpStatus.OK : HttpStatus.BAD_REQUEST).body(imagePath);
    }

    @GetMapping(value = "image/{imageName}")
    public byte[] getImage(@PathVariable(value = "imageName") String imageName)  {

        try {
            return imageService.getImage(imageName);
        } catch (IOException e){
            logger.error(e.getMessage() , e);
            return null;
        }
    }
}
