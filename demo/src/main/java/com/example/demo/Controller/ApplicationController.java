package com.example.demo.Controller;

import com.example.demo.Model.*;
import com.example.demo.Service.ApplicationService;
import com.example.demo.Service.ProjectService;
import com.example.demo.Service.UserService;
import com.example.demo.dto.ApplyForTheProjectsDTO;
import com.example.demo.dto.FileResponseDTO;
import com.example.demo.dto.LanguageDetailsDTO;
import com.example.demo.dto.UserDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping(value ="api/v1/user")
@CrossOrigin(origins ="*", allowedHeaders = "*")
public class ApplicationController {
    @Autowired
    private FileController fileController;
    @Autowired
    private ApplicationService applicationService;
    @Autowired
    private UserService userService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private ModelMapper modelMapper;


    @PostMapping("/postApplications")
    public String uploadApplicationFile(@RequestParam("file") MultipartFile file,  @RequestParam("appliedProjects") String projectJson, @AuthenticationPrincipal UserDetails userDetails) throws Exception {
        System.out.println("hihi");
        System.out.println(projectJson);
        ResponseEntity<FileResponseDTO> applicationDetails= fileController.uploadFile(file);
        ObjectMapper objectMapper = new ObjectMapper();

        ApplyForTheProject applyForTheProject = objectMapper.readValue(projectJson,ApplyForTheProject.class);
        UserDTO user = userService.findUserID(userDetails);
        System.out.println(applyForTheProject);
        applyForTheProject.setFreelancerID(user.getUserID());

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


    @GetMapping("/ViewAllApplicationCardsForTheProject/{projectIDD}")
    public Map<String, Object> getAllApplicaitonDetails(@AuthenticationPrincipal UserDetails userDetails,@PathVariable String projectIDD){
        System.out.println(projectIDD);
        UserDTO user = userService.findUserID(userDetails);
        List<ApplyForTheProject> applyForTheProjects =  applicationService.findByProjectID(projectIDD);
        // applicationService.findByProjectID(projectIDD);
       List<ApplyForTheProjectsDTO> applyForTheProjectsDTOS = modelMapper.map(applyForTheProjects, new TypeToken<List<ApplyForTheProjectsDTO>>(){}.getType());
       // List<Optional<Project>> projects = new ArrayList<>();
      /*  for(ApplyForTheProjectsDTO a : applyForTheProjectsDTOS){
            String projectID = a.getProjectIDD();
           Optional<Project> project =  projectService.getProjectsByID(Long.parseLong(projectID));
            a.setProjectTitle(project.get().getProjectTitle());
            a.setClientID(project.get().getFk_userID().getClientID());
        }*/

        Map<String,Object> response = new HashMap<>();
        response.put("Applications",applyForTheProjectsDTOS);

        return response;

    }

    @GetMapping("/downloadApplication/{projectApplicationID}")
    public ResponseEntity<Object>  downloadApplication(@PathVariable String projectApplicationID) throws IOException {
        System.out.println(projectApplicationID);
        Optional<ApplyForTheProject> application = applicationService.getApplicationByID(Long.parseLong(projectApplicationID));
        System.out.println(application.get().getResumePath());

        File file = new File(application.get().getResumePath());
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        //  ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(file.toPath()));

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
    //  System.out.println(responseEntities);


}
