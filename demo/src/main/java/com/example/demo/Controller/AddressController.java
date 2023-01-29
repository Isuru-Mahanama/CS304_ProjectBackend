package com.example.demo.Controller;

import com.example.demo.ErrorHandling.Response;
import com.example.demo.Service.AddressService;
import com.example.demo.Service.UserService;
import com.example.demo.dto.AddressDTO;

import com.example.demo.dto.AddressEmailDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value ="api/v1/user")
@CrossOrigin(origins ="*")
public class AddressController {

    boolean success= false;
    @Autowired
    private UserService userService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private UserRepo userRepo;

    @PostMapping("/saveAddress")
    public ResponseEntity<Response> addAddress(@RequestBody AddressEmailDTO addressEmailDTO){

        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(addressEmailDTO.getEmail());
        UserDTO userDTO1 = userService.findUserID(userDTO);

        AddressDTO addressDTO1 = addressService.saveAddress(userDTO1,addressEmailDTO);
        if(addressDTO1 !=null){
            success = true;
        }
        String message = success ? "Address is added successfully " : " Address is not added";
        return new ResponseEntity<>(new Response(success, message), HttpStatus.OK);
    }
}
