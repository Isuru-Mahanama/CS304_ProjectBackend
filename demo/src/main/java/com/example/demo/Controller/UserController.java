package com.example.demo.Controller;

import com.example.demo.ErrorHandling.Response;
import com.example.demo.Service.UserService;
import com.example.demo.dto.UserDTO;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping(value ="api/v1/user")
@CrossOrigin(origins ="*" , allowedHeaders = "*")
public class UserController {

    boolean success = false;

    @Autowired
    private UserService userService;
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


}
