package com.example.demo.Controller;

import com.example.demo.Model.EngineeringSubcategories;
import com.example.demo.Model.Prize;
import com.example.demo.Service.PrizeService;
import com.example.demo.dto.LaguageLevelDetailsDTO;
import com.example.demo.dto.PrizeDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value ="api/v1/user")
@CrossOrigin(origins ="*")
public class PrizeController {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PrizeService prizeService;
    @GetMapping("/getPrizes")
    public List<Prize> getAllPrizes(){
        List<Prize> prizeList = prizeService.getAllPrizes();

        return prizeList;
    }
}
