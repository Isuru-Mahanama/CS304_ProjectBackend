package com.example.demo.Service;

import com.example.demo.Model.CurrencyType;
import com.example.demo.Model.LanguageLevelDetails;
import com.example.demo.dto.CurrencyTypeDTO;
import com.example.demo.dto.LaguageLevelDetailsDTO;
import com.example.demo.repo.CurrencyRepo;
import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CurrencyService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CurrencyRepo currencyRepo;

    @PostConstruct
    public void run() throws Exception
    {
        CurrencyTypeDTO c1 = new CurrencyTypeDTO (1L,"USD");
        CurrencyTypeDTO  c2 = new CurrencyTypeDTO (2L,"CAR");
        CurrencyTypeDTO  c3 = new CurrencyTypeDTO (3L,"EUR");


        currencyRepo.save(modelMapper.map(c1, CurrencyType.class));
        currencyRepo.save(modelMapper.map(c2, CurrencyType.class));
        currencyRepo.save(modelMapper.map(c3, CurrencyType.class));


    }

    public List<CurrencyType> getAllCurencyTYpes() {
        return currencyRepo.findAll();
    }
}
