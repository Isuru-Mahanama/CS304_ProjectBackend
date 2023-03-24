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
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
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


    @GetMapping("/getAllApplicationDetails")
    public Map<String, Object> getAllprojectDetails(@AuthenticationPrincipal UserDetails userDetails){
        UserDTO user = userService.findUserID(userDetails);
        List<ApplyForTheProject> applyForTheProjects =  applicationService.findByuserID(user.getUserID());

        List<ApplyForTheProjectsDTO> applyForTheProjectsDTOS = modelMapper.map(applyForTheProjects, new TypeToken<List<ApplyForTheProjectsDTO>>(){}.getType());
        List<Optional<Project>> projects = new ArrayList<>();
        for(ApplyForTheProjectsDTO a : applyForTheProjectsDTOS){
            String projectID = a.getProjectIDD();
           Optional<Project> project =  projectService.getProjectsByID(Long.parseLong(projectID));
            a.setProjectTitle(project.get().getProjectTitle());
            a.setClientID(project.get().getFk_userID().getClientID());
        }
     //   Optional<Freelancer> freelancer = freelancerService.getAllDetals(user.getUserID());
     //   Optional<Language> language = languageService.getLanguagesByID(user.getUserID());
     //   String city = addressService.getCityByID(user.getUserID());
     //   List<Project> projects = projectService.getAllProjectDetails();
        Map<String,Object> response = new HashMap<>();
        response.put("Applications",applyForTheProjectsDTOS);
       // response.put("Projects",projects);
     //   response.put("Languages",language);
      //  response.put("UserName",user.getUserNames());
      //  response.put("City",city);
        return response;

    }
}
