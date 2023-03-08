package com.example.demo.Service;

import com.example.demo.Model.EngineeringSubcategories;
import com.example.demo.Model.LanguageLevelDetails;
import com.example.demo.dto.EngineeringCategoriesDTO;
import com.example.demo.dto.LaguageLevelDetailsDTO;
import com.example.demo.repo.EngineeringCategoriesRepo;
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
public class EngineeringSubCategoriesService {

    @Autowired
    private EngineeringCategoriesRepo engineeringCategoriesRepo;
    @Autowired
    private ModelMapper modelMapper;
    @PostConstruct
    public void run() throws Exception
    {
        EngineeringCategoriesDTO e1 = new EngineeringCategoriesDTO(3000L,"Civil Engineering");
        EngineeringCategoriesDTO e2 = new EngineeringCategoriesDTO(3001L,"Environmental engineering");
        EngineeringCategoriesDTO e3 = new EngineeringCategoriesDTO(3002L,"Geotechnical engineering");
        EngineeringCategoriesDTO e4 = new EngineeringCategoriesDTO(3003L,"Structural engineering");
        EngineeringCategoriesDTO e5 = new EngineeringCategoriesDTO(3004L,"Transport engineering");

        engineeringCategoriesRepo.save(modelMapper.map(e1, EngineeringSubcategories.class));
        engineeringCategoriesRepo.save(modelMapper.map(e2, EngineeringSubcategories.class));
        engineeringCategoriesRepo.save(modelMapper.map(e3, EngineeringSubcategories.class));
        engineeringCategoriesRepo.save(modelMapper.map(e4, EngineeringSubcategories.class));
        engineeringCategoriesRepo.save(modelMapper.map(e5, EngineeringSubcategories.class));

    }

    public List<EngineeringCategoriesDTO> getAllEngineeringSubCategory() {
        List<EngineeringSubcategories> engineeringSubcategories = engineeringCategoriesRepo.findAll();
        return modelMapper.map(engineeringSubcategories, new TypeToken<List<EngineeringCategoriesDTO>>(){}.getType());
    }
}
