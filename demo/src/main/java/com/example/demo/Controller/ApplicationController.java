package com.example.demo.Controller;

import com.example.demo.Model.ApplyForTheProject;
import com.example.demo.Service.ApplicationService;
import com.example.demo.dto.FileResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;

@RestController
@RequestMapping(value ="api/v1/user")
@CrossOrigin(origins ="*", allowedHeaders = "*")
public class ApplicationController {
    @Autowired
    private FileController fileController;
    @Autowired
    private ApplicationService applicationService;


    @PostMapping("/postApplications")
    public String uploadApplicationFile(@RequestParam("file") MultipartFile file,  @RequestParam("appliedProjects") String projectJson) throws Exception {
        System.out.println("hihi");
        System.out.println(projectJson);
        ResponseEntity<FileResponseDTO> applicationDetails= fileController.uploadFile(file);
        ObjectMapper objectMapper = new ObjectMapper();

        ApplyForTheProject applyForTheProject = objectMapper.readValue(projectJson,ApplyForTheProject.class);
        System.out.println(applyForTheProject);

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String filePath = "D:/ServerForMyProject/" + file.getOriginalFilename();
        applyForTheProject.setResumeName(fileName);
        applyForTheProject.setResumePath(filePath);

        applicationService.saveApplicationDetails(applyForTheProject,fileName,filePath);

        File convertFile = new File(filePath);

        convertFile.createNewFile();
        try(FileOutputStream fout = new FileOutputStream(convertFile))
        {
            fout.write(file.getBytes());
        }
        catch(Exception exe)
        {
            exe.printStackTrace();
        }


        return "file has uploaded successfully";
    }

}
