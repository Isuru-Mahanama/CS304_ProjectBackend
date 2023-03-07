package com.example.demo.Service;

import com.example.demo.Model.Project;
import com.example.demo.dto.ProjectDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.repo.ProjectRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProjectService {
    @Autowired
    private ProjectRepo projectRepo;
    @Autowired
    private ModelMapper modelMapper;

    public void saveProjectDetails(ProjectDTO projectJson) {

        System.out.println("In servive class"+projectJson);
        projectRepo.save(modelMapper.map(projectJson, Project.class));
    }
}
