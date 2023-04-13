package com.example.demo.Controller;

import com.example.demo.ErrorHandling.Response;
import com.example.demo.Model.*;
import com.example.demo.Service.AddressService;
import com.example.demo.Service.ClientService;
import com.example.demo.Service.LanguageService;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(value ="api/v1/user")
@CrossOrigin(origins ="*", allowedHeaders = "*")
public class ClientController {

    public String loggedInEmail = "";
    private ClientDTO clientLoggedIn = null;
    boolean success = false;
    @Autowired
    private ClientService clientService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private LanguageService languageService;
    @Autowired
    private AddressService addressService;

    @PostMapping("/setUpClient")
    public ResponseEntity<Response> createClient(@RequestBody UserDTO userDTO, @AuthenticationPrincipal UserDetails userDetails) {

        UserDTO userID = userService.findUserID(userDetails);
        boolean clientExistitng = clientService.findeExisting(userID.getUserID());
        System.out.println(clientExistitng);
        if(clientExistitng == false) {
            ClientDTO savedClient = clientService.saveClient(userID.getUserID());
            if (savedClient != null) {
                success = true;
            }
            String message = success ? "Client is saved successfully" : "Error saving user";
            return new ResponseEntity<>(new Response(success, message), HttpStatus.OK);
        }
        success = true;
        String message = success ? "Client is already saved" : "Error saving user";
        return new ResponseEntity<>(new Response(success, message), HttpStatus.OK);
    }

    @PutMapping("/setUpClientAccount")
    public ResponseEntity<Response> setUpClientAccount(@RequestBody ClientDTO clientDTO, @AuthenticationPrincipal UserDetails userDetails) {
        System.out.println("Client" + clientDTO);
        ClientDTO findClient = clientService.findUserID(userDetails, clientDTO);

        ClientDTO setUpClientAccount = clientService.accountSetUpClient(findClient);

        if (setUpClientAccount != null) {
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

    @GetMapping("/ClientAndUserDetails/{clientID}")
    public Map<String, Object> getAllprojectDetails(@PathVariable String clientID) {
        System.out.println("clientID");
        System.out.println(clientID);
        List<User> user = userService.findUserByID();
        System.out.println(user);
        Optional<Client> client = clientService.getAllDetals(Long.parseLong(clientID));
        //   Optional<Language> language = languageService.getLanguagesByID(user.getUserID());
        // String city = addressService.getCityByID(user.getUserID());
        Map<String, Object> response = new HashMap<>();
        for (User u : user) {
            if (u.getUserID() == Long.parseLong(clientID)) {
                response.put("UserName",u.getUserNames());
                response.put("FirstName",u.getFirstName());
                response.put("LastName",u.getLastName());
                response.put("Address",u.getAddress());
                response.put("Company",u.getCompany());
                response.put("DisplayEmail",u.getDisplayEmail());

            }
            }
            System.out.println(user.get(0).getCompany());

       /* if (user.isEmpty() ) {
            UserResponse response = new UserResponse(user.get().getUsername(), user.get().getEmail());
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }*/

            //  response.put("Projects",projects);

            response.put("Client", client);
            // response.put("Languages",language);
            //   response.put("User",user);
            //response.put("City",city);
            return response;

        }


        //retrieving the details from client table to fill the client form
        @GetMapping("/retrieveClientDetails")
        public Map<String, Object> getclientDetails(@AuthenticationPrincipal UserDetails userDetails){

            UserDTO user = userService.findUserID(userDetails);

            Optional<Client> client = clientService.getAllDetals(user.getUserID());
            System.out.println("halloits project details1");

            Map<String,Object> response = new HashMap<>();

            response.put("ClientDetails",client.get());


            System.out.println("rsponse"+response);
            return response;

        }

}
