package com.example.demo.Service;

import com.example.demo.Model.ConstructionSubCategories;
import com.example.demo.Model.EngineeringSubcategories;
import com.example.demo.dto.ConstructionSubCategoriesDTO;
import com.example.demo.dto.EngineeringCategoriesDTO;
import com.example.demo.repo.ConstructionSubCategoriesRepo;
import com.example.demo.repo.EngineeringCategoriesRepo;
import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ConstructionSubCategoriesService {
    @Autowired
    private ConstructionSubCategoriesRepo constructionSubCategoriesRepo;
    @Autowired
    private ModelMapper modelMapper;
    @PostConstruct
    public void run() throws Exception
    {
        ConstructionSubCategoriesDTO c1 = new ConstructionSubCategoriesDTO(1L,"General Requirement");
        ConstructionSubCategoriesDTO c2 = new ConstructionSubCategoriesDTO(2L,"Site Works");
        ConstructionSubCategoriesDTO c3 = new ConstructionSubCategoriesDTO(3L," Concrete");
        ConstructionSubCategoriesDTO c4 = new ConstructionSubCategoriesDTO(4L,"Masonry");
        ConstructionSubCategoriesDTO c5 = new ConstructionSubCategoriesDTO(5L," Metals");
        ConstructionSubCategoriesDTO c6 = new ConstructionSubCategoriesDTO(6L," Wood and Plastics");
        ConstructionSubCategoriesDTO c7 = new ConstructionSubCategoriesDTO(7L," Thermal and Moisture Protection");
        ConstructionSubCategoriesDTO c8 = new ConstructionSubCategoriesDTO(8L," Doors and Windows");
        ConstructionSubCategoriesDTO c9 = new ConstructionSubCategoriesDTO(9L," Finishes");

        constructionSubCategoriesRepo.save(modelMapper.map(c1, ConstructionSubCategories.class));
        constructionSubCategoriesRepo.save(modelMapper.map(c2, ConstructionSubCategories.class));
        constructionSubCategoriesRepo.save(modelMapper.map(c3, ConstructionSubCategories.class));
        constructionSubCategoriesRepo.save(modelMapper.map(c4, ConstructionSubCategories.class));
        constructionSubCategoriesRepo.save(modelMapper.map(c5, ConstructionSubCategories.class));
        constructionSubCategoriesRepo.save(modelMapper.map(c6, ConstructionSubCategories.class));
        constructionSubCategoriesRepo.save(modelMapper.map(c7, ConstructionSubCategories.class));
        constructionSubCategoriesRepo.save(modelMapper.map(c8, ConstructionSubCategories.class));
        constructionSubCategoriesRepo.save(modelMapper.map(c9, ConstructionSubCategories.class));

    }

    public List<ConstructionSubCategoriesDTO> getAllCategories() {
        List<ConstructionSubCategories> constructionSubCategories = constructionSubCategoriesRepo.findAll();
        return modelMapper.map(constructionSubCategories,new TypeToken<List<ConstructionSubCategoriesDTO>>(){}.getType());
    }
}
