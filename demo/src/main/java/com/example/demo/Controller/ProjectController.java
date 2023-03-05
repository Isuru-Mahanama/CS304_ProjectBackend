package com.example.demo.Controller;

import com.example.demo.ErrorHandling.Response;
import com.example.demo.Service.ProjectService;
import com.example.demo.dto.ProjectDTO;
import com.example.demo.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value ="api/v1/user")
@CrossOrigin(origins ="*")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @PostMapping("/postProject")
    public void postUser(@RequestBody ProjectDTO projectDTO){
    System.out.println(projectDTO.getEcategory());
    }
}
