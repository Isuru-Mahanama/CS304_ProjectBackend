package com.example.demo.Service;

import com.example.demo.Model.ApplyForTheProject;
import com.example.demo.repo.ApplicationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationService {
    @Autowired
    private ApplicationRepo applicationRepo;
    private Long logedUser;

    public List<ApplyForTheProject> findByuserID(Long userID) {
        return applicationRepo.findByFreelancerID(userID);
    }

    public void saveApplicationDetails(ApplyForTheProject applyForTheProject, String fileName, String filePath) {
      System.out.println("Hallo");
     // System.out.println(logedUser);
     //   applyForTheProject.setFreelancerID(logedUser);
        applicationRepo.save(applyForTheProject);
    }

    public void saveLogedUser(Long userID) {
        this.setLoggedUser(userID);
        System.out.println(logedUser);
    }

    private void setLoggedUser(Long userID) {
        this.logedUser = userID;
    }
}
