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
    public void saveFile(String fileName, MultipartFile multipartFile, Long size) throws IOException {
        Path uploadDirectory = Paths.get("Files-Upload");

        try(InputStream inputStream = multipartFile.getInputStream()){
            Path filePath = uploadDirectory.resolve(fileName);
            Files.copy(inputStream,filePath, StandardCopyOption.REPLACE_EXISTING);
        }catch(IOException ioe){
            throw new IOException("Error saving uploaded file" + fileName,ioe);
        }

        // Create a new FileUplod entity and set its fields
        FileUplod fileUpload = new FileUplod();
        fileUpload.setFileName(fileName);
     //   fileUpload.setFileType(multipartFile.getContentType());
      //  fileUpload.setSize(size);
      //  fileUpload.setFile(multipartFile.getBytes());

        // Save the entity to the database
        fileUploadRepo.save(fileUpload);
    }

    public void uploadFile(MultipartFile file, String projectJson) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        // Save the file to a directory on the server
        String uploadDir = "/path/to/upload/directory/";
        File savedFile = new File(uploadDir + fileName);
        file.transferTo(savedFile);

        // Get the absolute path of the saved file
        String absolutePath = savedFile.getAbsolutePath();

        // Save the file to the database or any other storage
       // fileUploadRepo.saveFile(fileName, absolutePath);
    }

}
