package com.example.demo.Controller;

import com.example.demo.ErrorHandling.Response;
import com.example.demo.Model.*;
import com.example.demo.Service.AddressService;
import com.example.demo.Service.FreelancerService;
import com.example.demo.Service.LanguageService;
import com.example.demo.Service.UserService;
import com.example.demo.dto.UserDTO;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping(value ="api/v1/user")
@CrossOrigin(origins ="*" , allowedHeaders = "*")
public class UserController {

    boolean success = false;

    @Autowired
    private UserService userService;
    @Autowired
    private LanguageService languageService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private FreelancerService freelancerService;
    @GetMapping("/getUser")
    public String getUser(){
        return "User is saved";
    }

    @PostMapping("/saveUser")
    public ResponseEntity<Response> postUser(@RequestBody UserDTO userDTO){

        UserDTO savedUser = userService.saveUser(userDTO);


        if(savedUser!=null){
             success = true;
        }
        String message = success ? "User saved successfully" : "Error saving user";
        return new ResponseEntity<>(new Response(success, message), HttpStatus.OK);
    }
    @PutMapping("/saveUserName")
     public ResponseEntity<Response> putUserName(@AuthenticationPrincipal UserDetails userDetails, @RequestBody UserDTO userDTO ){


        UserDTO updateUserName = userService.saveUserName(userDTO,userDetails);
        if(updateUserName != null){
            success= true;
         }
         String message = success ? "User saved successfully" : "User name saved incorrectly";
         return new ResponseEntity<>(new Response(success, message), HttpStatus.OK);

     }

    @PutMapping("/setUpUserAccount")
    public ResponseEntity<Response> setUpUserAccountPersonalDetails(@AuthenticationPrincipal UserDetails userDetails, @RequestBody UserDTO userDTO){

        System.out.println("afaa");
        UserDTO findUser = userService.findUserID(userDetails);
        UserDTO setUpPersonalAccount = userService.setUpPersonalAccount(findUser,userDTO);

        if(setUpPersonalAccount!=null){
            success = true;
        }
        String message = success ? "User saved successfully" : "Error saving user";
        return new ResponseEntity<>(new Response(success, message), HttpStatus.OK);
    }
    @GetMapping("/getUserName")
    public String getUserName(@AuthenticationPrincipal UserDetails userDetails){

        System.out.println("afaa");
        UserDTO findUser = userService.findUserID(userDetails);
        System.out.println( "UserName"+findUser.getUserNames());
        return findUser.getUserNames();
    }

    @GetMapping("/getalluserandfreelancerDetais")
    public Map<String, Object> getAllUserDetailsANDFreelancerDetails(@AuthenticationPrincipal UserDetails userDetails){

        UserDTO user = userService.findUserID(userDetails);

       // Optional<Client> client = clientService.getAllDetals(user.getUserID());
        System.out.println("halloits project details1");
        Optional<Language> language = languageService.getLanguagesByID(user.getUserID());
        Optional<Address> address = addressService.getAddressByID(user.getUserID());

         Optional<Freelancer> freelancer = freelancerService.getAllDetals(user.getUserID());

        System.out.println("halloits project details2");
        Map<String,Object> response = new HashMap<>();
        String phoneNumber = "" +user.getPhoneNumber();


        response.put("Languages",language);
        response.put("UserName",user.getUserNames());
        response.put("FirstName",user.getFirstName());
        response.put("LastName",user.getLastName());
        response.put("DisplayEmail",user.getDisplayEmail());
        response.put("PostalCode",user.getPostalCode());
        response.put("Company",user.getCompany());
        response.put("Location",user.getLocation());
        response.put("PhoneNumber",phoneNumber );
        response.put("TimeZone",user.getTimeZone());

        response.put("Address",address);
        System.out.println("halloits project details3");
        response.put("Freelncer",freelancer);
        System.out.println("rsponse"+response);
        return response;

    }



}
