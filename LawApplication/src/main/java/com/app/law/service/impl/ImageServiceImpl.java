package com.app.law.service.impl;

import com.app.law.dto.ImageDto;
import com.app.law.service.ImageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by https://github.com/kwanpham
 */
@Service
public class ImageServiceImpl implements ImageService {


    private  String uploadRootPath = "D:\\image" ;

    @Override
    public boolean uploadImage(HttpServletRequest request, ImageDto imageDto) {
        String description = imageDto.getDescription();

        // Thư mục gốc upload file.
        //uploadRootPath = request.getServletContext().getRealPath("upload");
        System.out.println("uploadRootPath=" + uploadRootPath);

        File uploadRootDir = new File(uploadRootPath);
        // Tạo thư mục gốc upload nếu nó không tồn tại.
        if (!uploadRootDir.exists()) {
            uploadRootDir.mkdirs();
        }
        MultipartFile[] fileDatas = imageDto.getFileDatas();
        //
        List<File> uploadedFiles = new ArrayList<>();
        List<String> failedFiles = new ArrayList<>();


        for (MultipartFile fileData : fileDatas) {

            // Tên file gốc tại Client.
            String name = fileData.getOriginalFilename();
            String[] s = name.split(".");

            name = System.currentTimeMillis() + "." + s[s.length - 1]; //đặt tên ảnh theo thời gian upload
            System.out.println("Client File Name = " + name);

            if (name != null && name.length() > 0) {
                try {
                    // Tạo file tại Server.
                    File serverFile = new File(uploadRootDir.getAbsolutePath() + File.separator + name);

                    BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                    stream.write(fileData.getBytes());
                    stream.close();
                    //
                    uploadedFiles.add(serverFile);
                    return true;

                } catch (Exception e) {

                    System.out.println("Error Write file: " + name);
                    failedFiles.add(name);
                    return false;
                }
            }
        }
        return false;
    }

    @Override
    public String uploadImage(MultipartFile imageFile) {
        System.out.println("uploadRootPath=" + uploadRootPath);

        File uploadRootDir = new File(uploadRootPath);
        // Tạo thư mục gốc upload nếu nó không tồn tại.
        if (!uploadRootDir.exists()) {
            uploadRootDir.mkdirs();
        }

        // Tên file gốc tại Client.
        String name = imageFile.getOriginalFilename();
//        String[] s = name.split(".");

        name = System.currentTimeMillis() + "." + name; //đặt tên ảnh theo thời gian upload
        System.out.println("Client File Name = " + name);

        if (name.length() > 0) {
            try {
                // Tạo file tại Server.
                File serverFile = new File(uploadRootDir.getAbsolutePath() + File.separator + name);

                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                stream.write(imageFile.getBytes());
                stream.close();
                return uploadRootDir.getAbsolutePath() + File.separator + name;

            } catch (Exception e) {
                System.out.println("Error Write file: " + name);
            }
        }
        return null;
    }

    @Override
    public byte[] getImage(String imageName) throws IOException {
        File serverFile = new File(uploadRootPath + File.separator + imageName + ".jpg");

        return Files.readAllBytes(serverFile.toPath());
    }
}
