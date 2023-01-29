package com.example.demo.Controller;

import com.example.demo.Service.LanguageLevelDetailsService;
import com.example.demo.dto.LaguageLevelDetailsDTO;
import com.example.demo.dto.LanguageDetailsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value ="api/v1/user")
@CrossOrigin(origins ="*")
public class LanguageLevelController {

    @Autowired
    private LanguageLevelDetailsService languageLevelDetailsService;
    @GetMapping("/getLanguageLevels")
    public List<LaguageLevelDetailsDTO> getAllLanguageLevels(){
        return languageLevelDetailsService.getAllLanguageLevels();
    }
}
