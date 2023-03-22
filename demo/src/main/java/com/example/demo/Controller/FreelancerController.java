package com.example.demo.Controller;

import com.example.demo.ErrorHandling.Response;
import com.example.demo.Model.Freelancer;
import com.example.demo.Model.Language;
import com.example.demo.Service.FreelancerService;
import com.example.demo.Service.LanguageService;
import com.example.demo.Service.UserService;

import com.example.demo.dto.FreelancerDTO;
import com.example.demo.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(value ="api/v1/user")
@CrossOrigin(origins ="*", allowedHeaders = "*")
public class FreelancerController {
    private boolean success;
    @Autowired
    private FreelancerService freelancerService;
    @Autowired
    private UserService userService;
    @Autowired
    private LanguageService languageService;

    @PostMapping("/setUpFreelancer")
    public ResponseEntity<Response> createFreelancer(@RequestBody UserDTO userDTO,@AuthenticationPrincipal UserDetails userDetails){

        UserDTO userID = userService.findUserID(userDetails);

        FreelancerDTO savedFreelancer = freelancerService.saveFreelancer(userID.getUserID());
       // this.clientLoggedIn = savedClient;
        if(savedFreelancer!=null){
            success = true;
        }
        String message = success ? "Freelancer is saved successfully" : "Error saving freelancer";
        return new ResponseEntity<>(new Response(success, message), HttpStatus.OK);
    }

    @GetMapping("/getFreelancerDetails")
    public Map<String, Object> getAllFreelancerDetails(){
        long userID = 52;
        Optional<Freelancer> freelancer = freelancerService.getAllDetals(userID);
        Optional<Language> language = languageService.getLanguagesByID(userID);

        Map<String,Object> response = new HashMap<>();
        response.put("FreelancerDetails",freelancer);
        response.put("Languages",language);

        return response;
    }

}
