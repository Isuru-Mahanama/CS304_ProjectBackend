package com.example.demo.Controller;

import com.example.demo.Service.FileUploadService;
import com.example.demo.Service.ProjectService;

import com.example.demo.dto.ProjectDTO;

import com.example.demo.repo.ProjectCategoriesRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@RestController
@RequestMapping(value ="api/v1/user")
@CrossOrigin(origins ="*")
public class ProjectController {
    @Autowired
    private FileUploadService fileUploadService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private ProjectCategoriesRepo projectCategoriesRepo;


    @PostMapping("/postProjectFile")
    public String uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("projectDTO") String projectJson) throws IOException {

       ObjectMapper objectMapper = new ObjectMapper();
       ProjectDTO projectDTO = objectMapper.readValue(projectJson, ProjectDTO.class);
        projectService.saveProjectDetails(projectDTO);

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String filePath = "D:/ServerForMyProject/" + file.getOriginalFilename();

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

