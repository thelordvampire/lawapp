package com.app.law.controller;

import com.app.law.dto.ImageDto;
import com.app.law.service.ImageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    private ImageService is;

    // POST: Sử lý Upload
    @RequestMapping(value = "/uploadOneFile", method = RequestMethod.POST)
    public ResponseEntity uploadOneFileHandlerPOST(HttpServletRequest request, @RequestBody ImageDto myUploadForm) {

        boolean result = is.uploadImage(request , myUploadForm);

        if(result){
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();



    }

    @GetMapping(value = "image/{imageName}")
    public byte[] getImage(@PathVariable(value = "imageName") String imageName)  {

        try {
            return is.getImage(imageName);
        } catch (IOException e){
            logger.error(e.getMessage() , e);
            return null;
        }
    }
}
