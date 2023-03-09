package com.example.demo.Service;

import com.example.demo.Model.Prize;

import com.example.demo.dto.PrizeDTO;
import com.example.demo.repo.PrizeRepo;
import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PrizeService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PrizeRepo prizeRepo;
    @PostConstruct
    public void run() throws Exception
    {
        PrizeDTO p1 = new PrizeDTO(1L,"USD","Micro Project",10.00 , 30.00);
        PrizeDTO p2 = new PrizeDTO(2L,"USD","Simple Project",30.00 , 250.00);
        PrizeDTO p3 = new PrizeDTO(3L,"USD","Very small project",250.00 , 750.00);
        PrizeDTO p4 = new PrizeDTO(4L,"USD","Small project",750.00 , 1500.00);
        PrizeDTO p5 = new PrizeDTO(5L,"USD","Medium project",1500.00 , 3000.00);
        PrizeDTO p6 = new PrizeDTO(6L,"USD","Large project",3000.00 , 5000.00);
        PrizeDTO p7 = new PrizeDTO(7L,"USD","Very Large project",10000.00 , 20000.00);
        PrizeDTO p8 = new PrizeDTO(8L,"USD","Huge Project",20000.00 , 50000.00);
        PrizeDTO p9 = new PrizeDTO(9L,"USD","Major project",3000.00 , 5000.00);




        prizeRepo.save(modelMapper.map(p1, Prize.class));
        prizeRepo.save(modelMapper.map(p2, Prize.class));
        prizeRepo.save(modelMapper.map(p3, Prize.class));
        prizeRepo.save(modelMapper.map(p4, Prize.class));
        prizeRepo.save(modelMapper.map(p5, Prize.class));
        prizeRepo.save(modelMapper.map(p6, Prize.class));
        prizeRepo.save(modelMapper.map(p7, Prize.class));
        prizeRepo.save(modelMapper.map(p8, Prize.class));
        prizeRepo.save(modelMapper.map(p9, Prize.class));


    }

    public List<Prize> getAllPrizes() {
        return  prizeRepo.findAll();

    }
}
