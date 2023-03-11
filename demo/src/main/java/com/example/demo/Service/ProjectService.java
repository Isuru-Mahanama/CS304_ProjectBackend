package com.example.demo.Service;

import com.example.demo.Model.Prize;
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
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProjectService {
    @Autowired
    private PrizeService prizeService;
    @Autowired
    private FileUploadService fileUploadService;
    @Autowired
    private ProjectRepo projectRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private SubCategoryService subCategoryService;

    public void saveProjectDetails(ProjectDTO projectJson,String fileName,String filePath,String imageName,String imagePath) {

        System.out.println("In servive class"+projectJson.getAcategory());
        Optional<Prize> prize = prizeService.findProjectPrizeDetails(projectJson.getProjectType());

        projectJson.setPrizeminimum(Double.toString(prize.get().getMinimumPrize()));
        projectJson.setPrizemaximum(Double.toString(prize.get().getMaximumPrize()));
        projectJson.setCurrencyType(prize.get().getPrizeType());
        projectJson.setProjectType(prize.get().getProjectType());

        //subCategoryRepo.save(modelMapper.map(projectJson.getAcategory(), Subcategory.class));
        Project project =  projectRepo.save(modelMapper.map(projectJson, Project.class));
        Long projectID = project.getProjectID();
        subCategoryService.saveSubCategories(projectID,projectJson);
        fileUploadService.saveFileDetails(projectID,fileName,filePath,imageName,imagePath);

    }


    public List<Project> getAllProjectDetails() {
        return projectRepo.findAll();
    }


}
