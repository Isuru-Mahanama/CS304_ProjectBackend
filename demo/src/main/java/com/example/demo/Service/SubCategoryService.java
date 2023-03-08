package com.example.demo.Service;

import com.example.demo.Model.Subcategory;
import com.example.demo.dto.ProjectDTO;
import com.example.demo.dto.SubcategoryDTO;
import com.example.demo.repo.SubCategoryRepo;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.util.List;

@Service
@Transactional
public class SubCategoryService {
    @Autowired
    private SubCategoryRepo subCategoryRepo;
    @Autowired
    private ModelMapper modelMapper;

    public void saveSubCategories(Long projectID, ProjectDTO projectJson) {
        List<Subcategory> asubcategories = modelMapper.map(projectJson.getAcategory(),List.class);
        List<Subcategory> esubcategories = modelMapper.map(projectJson.getEcategory(),List.class);
        List<Subcategory> csubcategories = modelMapper.map(projectJson.getCcategory(),List.class);

        System.out.println(projectID);
        ModelMapper modelMapper = new ModelMapper();
        Type listType = new TypeToken<List<SubcategoryDTO>>() {}.getType();

        List<SubcategoryDTO> AsubcategoryDTOs = modelMapper.map(asubcategories, listType);
        List<SubcategoryDTO> EsubcategoryDTOs = modelMapper.map(esubcategories, listType);
        List<SubcategoryDTO> CsubcategoryDTOs = modelMapper.map(csubcategories, listType);

        for(SubcategoryDTO a: AsubcategoryDTOs){
            Subcategory sub = new Subcategory();
            sub.setProject(projectID);
            sub.setEsubCategoryID(a.getEsubCategoryID());
            sub.setCategory("Architecture");
            sub.setEsubCategoryName(a.getEsubCategoryName());
            subCategoryRepo.save(sub);
        }

        for(SubcategoryDTO e: EsubcategoryDTOs){
            Subcategory sub = new Subcategory();
            sub.setProject(projectID);
            sub.setCategory("Engineering");
            sub.setEsubCategoryID(e.getEsubCategoryID());
            sub.setEsubCategoryName(e.getEsubCategoryName());
            subCategoryRepo.save(sub);
        }

        for(SubcategoryDTO c: CsubcategoryDTOs){
            Subcategory sub = new Subcategory();
            sub.setProject(projectID);
            sub.setCategory("Construction");
            sub.setEsubCategoryID(c.getEsubCategoryID());
            sub.setEsubCategoryName(c.getEsubCategoryName());
            subCategoryRepo.save(sub);
        }

    }
}
