package com.example.demo.Service;

import com.example.demo.Model.ArchitectureSubCategory;
import com.example.demo.Model.ConstructionSubCategories;
import com.example.demo.dto.ArchitectureSubCategoriesDTO;
import com.example.demo.dto.ConstructionSubCategoriesDTO;
import com.example.demo.repo.AchitectureSubCategoriesRepo;
import com.example.demo.repo.ConstructionSubCategoriesRepo;
import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ArchitectureSubCategoriesService {

    @Autowired
    private AchitectureSubCategoriesRepo achitectureSubCategoriesRepo;
    @Autowired
    private ModelMapper modelMapper;
    @PostConstruct
    public void run() throws Exception
    {
        ArchitectureSubCategoriesDTO c1 = new ArchitectureSubCategoriesDTO(1L,"Residential Architect");
        ArchitectureSubCategoriesDTO c2 = new ArchitectureSubCategoriesDTO(2L,"Green Design Architect");
        ArchitectureSubCategoriesDTO c3 = new ArchitectureSubCategoriesDTO(3L," Landscape Architect");
        ArchitectureSubCategoriesDTO c4 = new ArchitectureSubCategoriesDTO(4L,"Interior Designer");
        ArchitectureSubCategoriesDTO c5 = new ArchitectureSubCategoriesDTO(5L," Commercial/Actual Architect");
        ArchitectureSubCategoriesDTO c6 = new ArchitectureSubCategoriesDTO(6L," Urban Designers");
        ArchitectureSubCategoriesDTO c7 = new ArchitectureSubCategoriesDTO(7L," Industrial Architect");
        ArchitectureSubCategoriesDTO c8 = new ArchitectureSubCategoriesDTO(8L," Restoration Architect");


        achitectureSubCategoriesRepo.save(modelMapper.map(c1, ArchitectureSubCategory.class));
        achitectureSubCategoriesRepo.save(modelMapper.map(c2,  ArchitectureSubCategory.class));
        achitectureSubCategoriesRepo.save(modelMapper.map(c3,  ArchitectureSubCategory.class));
        achitectureSubCategoriesRepo.save(modelMapper.map(c4,  ArchitectureSubCategory.class));
        achitectureSubCategoriesRepo.save(modelMapper.map(c5,  ArchitectureSubCategory.class));
        achitectureSubCategoriesRepo.save(modelMapper.map(c6,  ArchitectureSubCategory.class));
        achitectureSubCategoriesRepo.save(modelMapper.map(c7,  ArchitectureSubCategory.class));
        achitectureSubCategoriesRepo.save(modelMapper.map(c8,  ArchitectureSubCategory.class));


    }
}
