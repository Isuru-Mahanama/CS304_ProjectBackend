package com.example.demo.Service;

import com.example.demo.Model.LanguageDetails;
import com.example.demo.Model.LanguageLevelDetails;
import com.example.demo.dto.LaguageLevelDetailsDTO;
import com.example.demo.dto.LanguageDetailsDTO;
import com.example.demo.repo.LanguageDetailsRepo;
import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LaguageDetailsService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private LanguageDetailsRepo languageDetailsRepo;



    @PostConstruct
    public void run() throws Exception
    {
        LanguageDetailsDTO l1= new LanguageDetailsDTO(1L,"English");
        LanguageDetailsDTO l2= new LanguageDetailsDTO(2L,"Spanish");
        LanguageDetailsDTO l3= new LanguageDetailsDTO(3L,"French");
        LanguageDetailsDTO l4= new LanguageDetailsDTO(4L,"German");
        LanguageDetailsDTO l5= new LanguageDetailsDTO(5L,"Italian");
        LanguageDetailsDTO l6= new LanguageDetailsDTO(6L,"Chinese");
        languageDetailsRepo.save(modelMapper.map(l1,LanguageDetails.class));
        languageDetailsRepo.save(modelMapper.map(l2,LanguageDetails.class));
        languageDetailsRepo.save(modelMapper.map(l3,LanguageDetails.class));
        languageDetailsRepo.save(modelMapper.map(l4,LanguageDetails.class));
        languageDetailsRepo.save(modelMapper.map(l5,LanguageDetails.class));
        languageDetailsRepo.save(modelMapper.map(l6,LanguageDetails.class));

    }

    public List<LanguageDetailsDTO> getAllLaguages(){
        List<LanguageDetails> languages = languageDetailsRepo.findAll();
        return modelMapper.map(languages, new TypeToken<List<LanguageDetailsDTO>>(){}.getType());
    }
}
