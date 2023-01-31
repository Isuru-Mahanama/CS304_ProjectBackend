package com.example.demo.Controller;

import com.example.demo.ErrorHandling.Response;
import com.example.demo.Service.FreelancerService;
import com.example.demo.Service.UserService;

import com.example.demo.dto.FreelancerDTO;
import com.example.demo.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value ="api/v1/user")
@CrossOrigin(origins ="*")
public class FreelancerController {
    private boolean success;
    @Autowired
    private FreelancerService freelancerService;
    @Autowired
    private UserService userService;
    @PostMapping("/setUpFreelancer")
    public ResponseEntity<Response> createFreelancer(@RequestBody UserDTO userDTO){

        UserDTO userID = userService.findUserID(userDTO);

        FreelancerDTO savedFreelancer = freelancerService.saveFreelancer(userID.getUserID());
       // this.clientLoggedIn = savedClient;
        if(savedFreelancer!=null){
            success = true;
        }
        String message = success ? "Freelancer is saved successfully" : "Error saving freelancer";
        return new ResponseEntity<>(new Response(success, message), HttpStatus.OK);
    }

}
