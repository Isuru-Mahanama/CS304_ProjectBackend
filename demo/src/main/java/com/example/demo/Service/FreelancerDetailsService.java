package com.example.demo.Service;

import com.example.demo.Model.Freelancer;
import com.example.demo.Model.FreelancerCategoryDetails;
import com.example.demo.Model.FreelancerCeritficatesDetails;
import com.example.demo.Model.User;
import com.example.demo.dto.*;
import com.example.demo.repo.FreelancerDetailsRepo;
import com.example.demo.repo.FreelancerRepo;
import com.example.demo.repo.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FreelancerDetailsService {
    @Autowired
    private FreelancerDetailsRepo freelancerDetailsRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private FreelancerRepo freelancerRepo;
    @Autowired
    private UserRepo userRepo;

    public void saveFreelancerDetails(UserDetails userDetails,FreelancerDetailsDTO freelancerDetailsDTO) {
        User user = userRepo.findUserByEmail(userDetails.getUsername());
        freelancerDetailsDTO.setFreelancerID(user.getUserID());
        freelancerDetailsRepo.save(modelMapper.map(freelancerDetailsDTO, Freelancer.class));
        Freelancer f =modelMapper.map(freelancerDetailsDTO, Freelancer.class);

        System.out.println(freelancerDetailsDTO.getAcategory());
        List<FreelancerCategoryDetails> freelancerCategoryDetailsList= new ArrayList<>();

        for(ArchitectureSubCategoriesDTO a: freelancerDetailsDTO.getAcategory()){
            FreelancerCategoryDetails architecture = new FreelancerCategoryDetails();
            architecture.setEsubCategoryID(a.getASubCategoryID());
            architecture.setEsubCategoryName(a.getASubCategoryName());
            architecture.setCategory("Architecture");
            freelancerCategoryDetailsList.add(architecture);
        }

        for(ConstructionSubCategoriesDTO c: freelancerDetailsDTO.getCcategory()){
            FreelancerCategoryDetails construction = new FreelancerCategoryDetails();
            construction.setEsubCategoryID(c.getCSubCategoryID());
            construction.setEsubCategoryName(c.getCSubCategoryName());
            construction.setCategory("Construction");
            freelancerCategoryDetailsList.add(construction);
        }

        for(SubcategoryDTO e: freelancerDetailsDTO.getEcategory()){
            FreelancerCategoryDetails engineering = new FreelancerCategoryDetails();
            engineering.setEsubCategoryID(e.getEsubCategoryID());
            engineering.setEsubCategoryName(e.getEsubCategoryName());
            engineering.setCategory("Engineering");
            freelancerCategoryDetailsList.add(engineering);
        }
        f.setCategoryDetails(freelancerCategoryDetailsList);

        freelancerRepo.save(f);
    }


}
