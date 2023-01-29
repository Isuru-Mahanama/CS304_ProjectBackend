package com.example.demo.Controller;

import com.example.demo.Service.LaguageDetailsService;
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
public class LaguageDetailsController {
    @Autowired
    private LaguageDetailsService laguageDetailsService;

    @GetMapping("/getLanguages")
    public List<LanguageDetailsDTO> getAllLanguages(){
        return laguageDetailsService.getAllLaguages();
    }
}
