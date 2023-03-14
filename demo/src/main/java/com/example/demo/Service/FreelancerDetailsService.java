package com.example.demo.Service;

import com.example.demo.Model.Freelancer;
import com.example.demo.Model.FreelancerCeritficatesDetails;
import com.example.demo.Model.User;
import com.example.demo.dto.FreelancerDetailsDTO;
import com.example.demo.repo.FreelancerDetailsRepo;
import com.example.demo.repo.FreelancerRepo;
import com.example.demo.repo.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

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

    public void saveFreelancerDetails(FreelancerDetailsDTO freelancerDetailsDTO) {
        User user = userRepo.findUserByEmail(freelancerDetailsDTO.getEmail());
        freelancerDetailsDTO.setFreelancerID(user.getUserID());
        freelancerDetailsRepo.save(modelMapper.map(freelancerDetailsDTO, Freelancer.class));
        Freelancer f =modelMapper.map(freelancerDetailsDTO, Freelancer.class);
        System.out.println(f);
    }
}
