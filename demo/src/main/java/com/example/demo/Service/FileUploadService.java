package com.example.demo.Service;

import com.example.demo.Model.FileUplod;
import com.example.demo.repo.FileUploadRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
@Transactional
public class FileUploadService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private FileUploadRepo fileUploadRepo;

    public void saveFileDetails(Long projectID, String fileName, String filePath,String imageName,String imagePath) {
        FileUplod fileUplod= new FileUplod(projectID,fileName,filePath,imageName,imagePath);
        fileUploadRepo.save(fileUplod);
    }
}
