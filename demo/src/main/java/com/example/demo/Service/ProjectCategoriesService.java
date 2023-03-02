package com.example.demo.Service;

import com.example.demo.Model.LanguageLevelDetails;
import com.example.demo.Model.ProjectCategories;
import com.example.demo.dto.LaguageLevelDetailsDTO;
import com.example.demo.dto.ProjectCategoriesDTO;
import com.example.demo.repo.LanguageLevelDetailsRepo;
import com.example.demo.repo.ProjectCategoriesRepo;
import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProjectCategoriesService {
    @Autowired
    private ProjectCategoriesRepo projectCategoriesRepo;
    @Autowired
    private ModelMapper modelMapper;

    @PostConstruct
    public void run() throws Exception
    {
        ProjectCategoriesDTO c1 = new ProjectCategoriesDTO(1L,"Engineering");
        ProjectCategoriesDTO c2 = new ProjectCategoriesDTO(2L,"Construction");
        ProjectCategoriesDTO c3 = new ProjectCategoriesDTO(3L,"Architecture");


        projectCategoriesRepo.save(modelMapper.map(c1, ProjectCategories.class));
        projectCategoriesRepo.save(modelMapper.map(c2, ProjectCategories.class));
        projectCategoriesRepo.save(modelMapper.map(c3, ProjectCategories.class));
    }
}
