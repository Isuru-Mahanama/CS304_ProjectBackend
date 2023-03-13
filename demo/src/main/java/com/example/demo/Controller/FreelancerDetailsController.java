package com.example.demo.Controller;

import com.example.demo.dto.FreelancerDTO;
import com.example.demo.dto.FreelancerDetailsDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value ="api/v1/user")
@CrossOrigin(origins ="*")
public class FreelancerDetailsController {
    @PostMapping("/postEducationDetails")
    public void getAllDetails(@RequestBody FreelancerDetailsDTO freelancerDetailsDTO){
        System.out.println(freelancerDetailsDTO);
    }
}
