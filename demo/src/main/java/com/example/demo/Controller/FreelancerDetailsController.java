package com.example.demo.Controller;

import com.example.demo.Service.ArchitectureSubCategoriesService;
import com.example.demo.Service.FreelancerDetailsService;
import com.example.demo.dto.ArchitectureSubCategoriesDTO;
import com.example.demo.dto.FreelancerDTO;
import com.example.demo.dto.FreelancerDetailsDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value ="api/v1/user")
@CrossOrigin(origins ="*", allowedHeaders = "*")
public class FreelancerDetailsController {
    @Autowired
    private FreelancerDetailsService freelancerDetailsService;
    @PostMapping("/postEducationDetails")
    public void getAllDetails( @AuthenticationPrincipal UserDetails userDetails,@RequestBody FreelancerDetailsDTO freelancerDetailsDTO){
       System.out.println("posting Eduction Details");

        freelancerDetailsService.saveFreelancerDetails(userDetails,freelancerDetailsDTO);

    }
}
