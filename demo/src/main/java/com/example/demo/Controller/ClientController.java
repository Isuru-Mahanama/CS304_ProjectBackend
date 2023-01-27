package com.example.demo.Controller;

import com.example.demo.ErrorHandling.Response;
import com.example.demo.Service.ClientService;
import com.example.demo.Service.UserService;
import com.example.demo.dto.ClientDTO;
import com.example.demo.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value ="api/v1/client")
@CrossOrigin(origins ="*")
public class ClientController {

    boolean success = false;
    @Autowired
    private ClientService clientService;
    @Autowired
    private UserService userService;
    @PostMapping("/setUpClient")
    public ResponseEntity<Response> createClient(@RequestBody UserDTO userDTO){
        System.out.println(userDTO.getEmail());
        UserDTO userID = userService.findUserID(userDTO);

        ClientDTO  savedClient = clientService.saveClient(userID.getUserID());

        if(savedClient!=null){
            success = true;
        }
        String message = success ? "Client is saved successfully" : "Error saving user";
        return new ResponseEntity<>(new Response(success, message), HttpStatus.OK);
    }

}
