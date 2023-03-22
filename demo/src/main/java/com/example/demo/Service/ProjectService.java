package com.example.demo.Service;

import com.example.demo.Model.Prize;
import com.example.demo.Model.Project;
import com.example.demo.dto.ProjectDTO;

import com.example.demo.repo.ProjectRepo;
//import com.example.demo.repo.SubCategoryRepo;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

        System.out.println("passed proej");
        System.out.println(projectJson);
        System.out.println("In servive class"+projectJson.getAcategory());
        Optional<Prize> prize = prizeService.findProjectPrizeDetails(projectJson.getProjectType());

        projectJson.setPrizeminimum(Double.toString(prize.get().getMinimumPrize()));
        projectJson.setPrizemaximum(Double.toString(prize.get().getMaximumPrize()));
        projectJson.setCurrencyType(prize.get().getPrizeType());
        projectJson.setProjectType(prize.get().getProjectType());

        System.out.println(projectJson);


        //subCategoryRepo.save(modelMapper.map(projectJson.getAcategory(), Subcategory.class));
        Project project =  projectRepo.save(modelMapper.map(projectJson, Project.class));
        System.out.println(project);
        Long projectID = project.getProjectID();
        subCategoryService.saveSubCategories(projectID,projectJson);
        fileUploadService.saveFileDetails(projectID,fileName,filePath,imageName,imagePath);

    }


    public List<Project> getAllProjectDetails() {
        return projectRepo.findAll();
    }


    public Optional<Project> getProjectsByID(Long projectID) {
        return projectRepo.findById(projectID);
    }
}
