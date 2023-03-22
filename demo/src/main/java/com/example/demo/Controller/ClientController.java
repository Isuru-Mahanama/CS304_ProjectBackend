package com.example.demo.Controller;

import com.example.demo.ErrorHandling.Response;
import com.example.demo.Model.User;
import com.example.demo.Service.ClientService;
import com.example.demo.Service.UserService;
import com.example.demo.dto.ClientDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value ="api/v1/user")
@CrossOrigin(origins ="*", allowedHeaders = "*")
public class ClientController {

    public String loggedInEmail ="";
    private ClientDTO clientLoggedIn = null;
    boolean success = false;
    @Autowired
    private ClientService clientService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepo userRepo;

    @PostMapping("/setUpClient")
    public ResponseEntity<Response> createClient(@RequestBody UserDTO userDTO,@AuthenticationPrincipal UserDetails userDetails){

        UserDTO userID = userService.findUserID(userDetails);

        ClientDTO  savedClient = clientService.saveClient(userID.getUserID());
        if(savedClient!=null){
            success = true;
        }
        String message = success ? "Client is saved successfully" : "Error saving user";
        return new ResponseEntity<>(new Response(success, message), HttpStatus.OK);
    }

    @PutMapping("/setUpClientAccount")
    public ResponseEntity<Response> setUpClientAccount(@RequestBody ClientDTO clientDTO,@AuthenticationPrincipal UserDetails userDetails) {
        System.out.println("Client"+clientDTO);
        ClientDTO findClient = clientService.findUserID(userDetails,clientDTO);

        ClientDTO setUpClientAccount = clientService.accountSetUpClient(findClient);

        if(setUpClientAccount!=null){
            success = true;
        }
        String message = success ? "Client saved successfully" : "Error saving client";
        return new ResponseEntity<>(new Response(success, message), HttpStatus.OK);
    }

    @GetMapping("/checkclienttable")
    public boolean checkTable(@AuthenticationPrincipal UserDetails userDetails) {
        System.out.println("hh");
        User user = clientService.findUserByID(userDetails);
        boolean isTableFilled = clientService.isTableFilled(user.getUserID());
        if (isTableFilled) {
            return true;
        } else {
            return false;
        }
    }
}

