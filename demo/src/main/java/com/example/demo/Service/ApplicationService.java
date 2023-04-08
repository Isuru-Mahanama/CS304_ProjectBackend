package com.example.demo.Service;

import com.example.demo.Model.ApplyForTheProject;
import com.example.demo.repo.ApplicationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ApplicationService {
    @Autowired
    private ApplicationRepo applicationRepo;
    private Long logedUser;

    public List<ApplyForTheProject> findByuserID(Long userID) {
        return applicationRepo.findByFreelancerID(userID);
    }
    //Find project by projectID
    public List<ApplyForTheProject> findByProjectID(String projectIDD) {
       return applicationRepo.findByProjectIDD(projectIDD);
      //  System.out.println("i am in the applicationservivce");

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

    public Optional<ApplyForTheProject> getApplicationByID(long applicattionID) {
        return applicationRepo.findById(applicattionID);
    }
}
