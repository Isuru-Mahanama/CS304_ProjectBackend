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

    protected UserDTO loggedUser = null;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ApplicationService applicationService;
    public UserDTO saveUser(UserDTO userDTO){

        this.loggedUser = userDTO;
        User user = userRepo.save(modelMapper.map(userDTO, User.class));

        applicationService.saveLogedUser(user.getUserID());
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


    public UserDTO setUpPersonalAccount(UserDTO findUser, UserDTO userDTO) {

        if(findUser != null){
            Long userID = findUser.getUserID();
            userDTO.setUserID(userID);
            userDTO.setUserName(findUser.getUserName());
            User findUserClass = this.modelMapper.map(userDTO,User.class);
            userRepo.save(findUserClass);
            return userDTO;
        }
        return null;
    }
}
