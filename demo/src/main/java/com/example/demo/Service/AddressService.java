package com.example.demo.Service;

import com.example.demo.Model.Address;
import com.example.demo.dto.AddreessLanguageDTO;
import com.example.demo.dto.AddressDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.repo.AddreessRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    @Autowired
    private UserService userService;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private AddreessRepo addreessRepo;

    public AddressDTO saveAddress(UserDTO userDTO, AddreessLanguageDTO addreessLanguageDTO) {

        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setUserAddressID(userDTO.getUserID());
        addressDTO.setStreetAddress(addreessLanguageDTO.getStreetAddress());
        addressDTO.setCity(addreessLanguageDTO.getCity());
        addressDTO.setCountry(addreessLanguageDTO.getCountry());
        addressDTO.setProvince(addreessLanguageDTO.getProvince());
        addreessRepo.save(modelMapper.map(addressDTO, Address.class));
        return addressDTO;
    }
}
