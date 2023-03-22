package com.example.demo.Service;
import com.example.demo.Model.Role;
import com.example.demo.Model.User;
import com.example.demo.dto.UserDTO;
import com.example.demo.repo.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
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

    public UserDTO saveUserName(UserDTO userDTO, UserDetails userDetails){
       // System.out.println(userDTO.getUserNames());
       // System.out.println(userDetails.getUsername());
        User userByEmail = userRepo.findUserByEmail(userDetails.getUsername());

        if(userByEmail !=null){
            userByEmail.setUserNames(userDTO.getUserNames());
            userRepo.save(userByEmail);
            return userDTO;
        }

        return null;
    }

    public UserDTO findUserID(UserDetails userDetails){

        User userById = userRepo.findUserByEmail(userDetails.getUsername());

        if(userById != null){
            return this.modelMapper.map(userById,UserDTO.class);
        }
        return null;
    }


    public UserDTO setUpPersonalAccount(UserDTO findUser, UserDTO userDTO) {

        if(findUser != null){
            findUser.setFirstName(userDTO.getFirstName());
            findUser.setLastName(userDTO.getLastName());
            findUser.setDisplayEmail(userDTO.getDisplayEmail());
            findUser.setPostalCode(userDTO.getPostalCode());
            findUser.setCompany(userDTO.getCompany());
            findUser.setLocation(userDTO.getLocation());
            findUser.setPhoneNumber(userDTO.getPhoneNumber());
            findUser.setTimeZone(userDTO.getTimeZone());

            User findUserClass = this.modelMapper.map(findUser,User.class);
            userRepo.save(findUserClass);
            return userDTO;
        }
        return null;
    }
}
