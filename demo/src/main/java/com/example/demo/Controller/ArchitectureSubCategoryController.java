package com.example.demo.Controller;

import com.example.demo.Service.ArchitectureSubCategoriesService;
import com.example.demo.dto.ArchitectureSubCategoriesDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value ="api/v1/user")
@CrossOrigin(origins ="*")
public class ArchitectureSubCategoryController {

    @Autowired
    private ArchitectureSubCategoriesService architectureSubCategoriesService;

    @GetMapping("/getAllArchtectureSubCategories")
    public List<ArchitectureSubCategoriesDTO> getAllSubCategories(){
        return architectureSubCategoriesService.getAllSubCategories();
    }
}
