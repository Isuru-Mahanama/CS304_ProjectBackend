package com.example.demo.Controller;

import com.example.demo.Service.ArchitectureSubCategoriesService;
import com.example.demo.Service.FreelancerDetailsService;
import com.example.demo.dto.ArchitectureSubCategoriesDTO;
import com.example.demo.dto.FreelancerDTO;
import com.example.demo.dto.FreelancerDetailsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value ="api/v1/user")
@CrossOrigin(origins ="*")
public class FreelancerDetailsController {
    @Autowired
    private FreelancerDetailsService freelancerDetailsService;
    @PostMapping("/postEducationDetails")
    public void getAllDetails(@RequestBody FreelancerDetailsDTO freelancerDetailsDTO){
       System.out.println(freelancerDetailsDTO.getEmail());

        freelancerDetailsService.saveFreelancerDetails(freelancerDetailsDTO);

    }
}
