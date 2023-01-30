package com.example.demo.Service;

import com.example.demo.Model.Address;
import com.example.demo.Model.Language;
import com.example.demo.dto.AddreessLanguageDTO;
import com.example.demo.dto.AddressDTO;
import com.example.demo.dto.LanguageDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.repo.LanguageRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LanguageService {
    @Autowired
    private LanguageRepo languageRepo;
    @Autowired
    private ModelMapper modelMapper;
    public LanguageDTO saveLanguages(UserDTO userDTO1, AddreessLanguageDTO addreessLanguageDTO) {
        LanguageDTO languageDTO = new LanguageDTO();
        languageDTO.setUserLanguageID(userDTO1.getUserID());
        languageDTO.setLanguage(addreessLanguageDTO.getLanguage());
        languageDTO.setLanguageLevel(addreessLanguageDTO.getLanguageLevel());
        System.out.println("Level"+ addreessLanguageDTO.getLanguageLevel());
        languageRepo.save(modelMapper.map(languageDTO, Language.class));
        return languageDTO;
    }
}
