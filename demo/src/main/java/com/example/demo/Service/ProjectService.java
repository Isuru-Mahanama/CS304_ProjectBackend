package com.example.demo.Service;

import com.example.demo.Model.Project;
import com.example.demo.Model.Subcategory;
import com.example.demo.dto.ProjectDTO;

import com.example.demo.dto.SubcategoryDTO;
import com.example.demo.repo.ProjectRepo;
//import com.example.demo.repo.SubCategoryRepo;
import com.example.demo.repo.SubCategoryRepo;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.util.List;

@Service
@Transactional
public class ProjectService {
    @Autowired
    private FileUploadService fileUploadService;
    @Autowired
    private ProjectRepo projectRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private SubCategoryService subCategoryService;

    public void saveProjectDetails(ProjectDTO projectJson,String fileName,String filePath) {

        System.out.println("In servive class"+projectJson.getAcategory());

        //subCategoryRepo.save(modelMapper.map(projectJson.getAcategory(), Subcategory.class));
        Project project =  projectRepo.save(modelMapper.map(projectJson, Project.class));
        Long projectID = project.getProjectID();
        subCategoryService.saveSubCategories(projectID,projectJson);
       fileUploadService.saveFileDetails(projectID,fileName,filePath);

    }


}
