package com.example.demo.Service;

import com.example.demo.Model.LanguageLevelDetails;
import com.example.demo.dto.LaguageLevelDetailsDTO;

import com.example.demo.repo.LanguageLevelDetailsRepo;
import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LanguageLevelDetailsService {
    @Autowired
    private LanguageLevelDetailsRepo languageLevelDetailsRepo;
    @Autowired
    private ModelMapper modelMapper;
    @PostConstruct
    public void run() throws Exception
    {
        LaguageLevelDetailsDTO l1 = new LaguageLevelDetailsDTO(1L,"Native");
        LaguageLevelDetailsDTO l2 = new LaguageLevelDetailsDTO(2L,"Fluent");
        LaguageLevelDetailsDTO l3 = new LaguageLevelDetailsDTO(3L,"Proficient");
        LaguageLevelDetailsDTO l4 = new LaguageLevelDetailsDTO(4L,"Intermediate");
        LaguageLevelDetailsDTO l5 = new LaguageLevelDetailsDTO(5L,"Beginner");

        languageLevelDetailsRepo.save(modelMapper.map(l1, LanguageLevelDetails.class));
        languageLevelDetailsRepo.save(modelMapper.map(l2, LanguageLevelDetails.class));
        languageLevelDetailsRepo.save(modelMapper.map(l3, LanguageLevelDetails.class));
        languageLevelDetailsRepo.save(modelMapper.map(l4, LanguageLevelDetails.class));
        languageLevelDetailsRepo.save(modelMapper.map(l5, LanguageLevelDetails.class));



    }

    public List<LaguageLevelDetailsDTO> getAllLanguageLevels() {
        List<LanguageLevelDetails> languageLevels = languageLevelDetailsRepo.findAll();
        return modelMapper.map(languageLevels, new TypeToken<List<LaguageLevelDetailsDTO>>(){}.getType());
    }
}
