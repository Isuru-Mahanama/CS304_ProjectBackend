package com.example.demo.Service;

import com.example.demo.Model.Freelancer;

import com.example.demo.dto.FreelancerDTO;
import com.example.demo.repo.FreelancerRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FreelancerService {
    @Autowired
    private FreelancerRepo freelancerRepo;
    @Autowired
    private ModelMapper modelMapper;

    public FreelancerDTO saveFreelancer(Long userID) {
        FreelancerDTO freelancerDTO= new FreelancerDTO();
        freelancerDTO.setFreelancerID(userID);
        freelancerRepo.save(modelMapper.map(freelancerDTO, Freelancer.class));
        return freelancerDTO;
    }
}
