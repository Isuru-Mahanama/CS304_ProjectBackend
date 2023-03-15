package com.example.demo.Controller;

import com.example.demo.Model.Project;
import com.example.demo.Model.Subcategory;
import com.example.demo.Service.ProjectService;

import com.example.demo.Service.SubCategoryService;
import com.example.demo.dto.FileResponseDTO;
import com.example.demo.dto.ProjectDTO;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value ="api/v1/user")
@CrossOrigin(origins ="*")
public class ProjectController {

    @Autowired
    private SubCategoryService subCategoryService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private FileController fileController;
    @PostMapping("/postProjectFile")
    public String uploadFile(@RequestParam("file") MultipartFile file,@RequestParam("image") MultipartFile image, @RequestParam("projectDTO") String projectJson) throws Exception {

        ResponseEntity<FileResponseDTO>  imageDetails= fileController.uploadFile(image);
       ObjectMapper objectMapper = new ObjectMapper();
       ProjectDTO projectDTO = objectMapper.readValue(projectJson, ProjectDTO.class);

        System.out.println(projectDTO.getProjectType());
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String filePath = "D:/ServerForMyProject/" + file.getOriginalFilename();

        //Saving the image
        String imageName = StringUtils.cleanPath(image.getOriginalFilename());
        String imagePath = imageDetails.getBody().getFileDownloadUri();


        projectService.saveProjectDetails(projectDTO,fileName,filePath,imageName,imagePath);

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

    @GetMapping("/getAllProjectDetails")
    public List<Project> getAllprojectDetails(){
        return projectService.getAllProjectDetails();
    }
    @GetMapping("/download")
    public List<ResponseEntity<Object>>  downloadFile() throws IOException {
        List<Project> projects = projectService.getAllProjectDetails();
        List<ResponseEntity<Object>> responseEntities = new ArrayList<>();
        System.out.println(projects.get(0).getFileUplod().getFilePath());
        ResponseEntity<Object> responseEntity = null;
        for (Project p : projects) {
            String filename = p.getFileUplod().getFilePath();
            System.out.println(filename);
            File file = new File(filename);
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
            //  ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(file.toPath()));

            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition",
                    String.format("attachment; filename=\"%s\"", file.getName()));
            headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
            headers.add("Pragma", "no-cache");
            headers.add("Expires", "0");

            responseEntity = ResponseEntity.ok().headers(headers)
                    .contentLength(file.length())
                    .contentType(MediaType.parseMediaType("application/txt")).body(resource);


             responseEntities.add(responseEntity);
        }
        //  System.out.println(responseEntities);

            return responseEntities;
    }

    @GetMapping("/downloadImages")
    public List<byte[]> downloadFiles() throws IOException {
        List<Project> projects = projectService.getAllProjectDetails();
        List<byte[]> fileContentsList = new ArrayList<>();
        for (Project p : projects) {
            String filename = p.getFileUplod().getFilePath();
            File file = new File(filename);
            byte[] fileContents = Files.readAllBytes(file.toPath());
            fileContentsList.add(fileContents);
        }
        return fileContentsList;
    }

    @GetMapping("/getfie")
    public ResponseEntity<Object> downloadFileONe() throws IOException
    {
        List<Project> projects = projectService.getAllProjectDetails();
        String filename = projects.get(0).getFileUplod().getFilePath();
        File file = new File(filename);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition",
                String.format("attachment; filename=\"%s\"", file.getName()));
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        ResponseEntity<Object> responseEntity = ResponseEntity.ok().headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/txt")).body(resource);

        return responseEntity;
    }

    @GetMapping("/viewallProjectDetails/{projectID}")
    public String getallprojectDetails(@PathVariable String projectID){
        Optional<Project> project = projectService.getProjectsByID(Long.parseLong(projectID));
        List<Subcategory> subcategories = subCategoryService.findProjectSubCategories(Long.parseLong(projectID));
        System.out.print(project);
        System.out.print(subcategories);

        return "bll";

    }

}


