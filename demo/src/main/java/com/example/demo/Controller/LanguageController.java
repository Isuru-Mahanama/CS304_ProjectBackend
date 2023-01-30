package com.example.demo.Controller;

import com.example.demo.ErrorHandling.Response;
import com.example.demo.Service.LanguageService;
import com.example.demo.Service.UserService;
import com.example.demo.dto.AddreessLanguageDTO;
import com.example.demo.dto.AddressDTO;
import com.example.demo.dto.LanguageDTO;
import com.example.demo.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value ="api/v1/user")
@CrossOrigin(origins ="*")
public class LanguageController {
    public boolean success;
    @Autowired
    private UserService userService;
    @Autowired
    private LanguageService languageService;

    @PostMapping("/saveLanguages")
    public ResponseEntity<Response> addLanguage(@RequestBody AddreessLanguageDTO addreessLanguageDTO){

        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(addreessLanguageDTO.getEmail());
        UserDTO userDTO1 = userService.findUserID(userDTO);

        LanguageDTO languageDTO = languageService.saveLanguages(userDTO1,addreessLanguageDTO);
        if(languageDTO !=null){
            success = true;
        }
        String message = success ? "Language Details are successfully added " : " Language is not added";
        return new ResponseEntity<>(new Response(success, message), HttpStatus.OK);
    }
}
