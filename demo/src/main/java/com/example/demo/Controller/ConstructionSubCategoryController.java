package com.example.demo.Controller;

import com.example.demo.Service.ConstructionSubCategoriesService;
import com.example.demo.dto.ConstructionSubCategoriesDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value ="api/v1/user")
@CrossOrigin(origins ="*", allowedHeaders = "*")
public class ConstructionSubCategoryController {
    @Autowired
    private ConstructionSubCategoriesService constructionSubCategoriesService;

    @GetMapping("/getAllConstructionSubCategories")
    public List<ConstructionSubCategoriesDTO> getAllCategories(){
        return constructionSubCategoriesService.getAllCategories();
    }
}
