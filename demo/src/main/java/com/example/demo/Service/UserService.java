package com.example.demo.Service;

import com.example.demo.Model.User;
import com.example.demo.dto.UserDTO;
import com.example.demo.repo.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ModelMapper modelMapper;
    public UserDTO saveUser(UserDTO userDTO){
    userRepo.save(modelMapper.map(userDTO, User.class));
    return userDTO;
}

    public UserDTO saveUserName(UserDTO userDTO){
        User userByEmail = userRepo.findUserByEmail(userDTO.getEmail());

        if(userByEmail !=null){
            userByEmail.setUserName(userDTO.getUserName());
            userRepo.save(userByEmail);
            return userDTO;
        }

        return null;
    }

    public UserDTO findUserID(UserDTO userDTO){
        User userById = userRepo.findUserByEmail(userDTO.getEmail());

        if(userById != null){
            return this.modelMapper.map(userById,UserDTO.class);
        }
        return null;
    }



}
