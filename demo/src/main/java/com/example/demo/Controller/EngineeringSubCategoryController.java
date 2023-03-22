package com.example.demo.Controller;

import com.example.demo.Service.EngineeringSubCategoriesService;
import com.example.demo.dto.EngineeringCategoriesDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value ="api/v1/user")
@CrossOrigin(origins ="*", allowedHeaders = "*")
public class EngineeringSubCategoryController {
    @Autowired
    private EngineeringSubCategoriesService engineeringSubCategoriesService;

    @GetMapping("/getEngineeringSubCategory")
    public List<EngineeringCategoriesDTO> getAllEngineeringSubCategory(){
        System.out.println("hicontrolller");
        return engineeringSubCategoriesService.getAllEngineeringSubCategory();
    }
}
