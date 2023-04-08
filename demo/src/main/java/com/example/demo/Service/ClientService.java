package com.example.demo.Service;

import com.example.demo.Model.*;
import com.example.demo.dto.ClientDTO;
import com.example.demo.repo.ClientRepo;
import com.example.demo.repo.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class ClientService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ClientRepo clientRepo;

    @Autowired
    private ModelMapper modelMapper;

    public ClientDTO saveClient(Long userID) {
        ClientDTO clientDTO= new ClientDTO();
        clientDTO.setClientID(userID);
        clientRepo.save(modelMapper.map(clientDTO, Client.class));
        return clientDTO;
    }

    public ClientDTO accountSetUpClient(ClientDTO clientDTO){
        clientRepo.save(modelMapper.map(clientDTO, Client.class));
        return clientDTO;
    }

    public ClientDTO findUserID(UserDetails userDetails,ClientDTO clientDTO) {
        User userById = userRepo.findUserByEmail(userDetails.getUsername());
        clientDTO.setClientID(userById.getUserID());
        if(clientDTO!= null){
            return clientDTO;
        }
        return null;
    }
    public User findUserByID(UserDetails userDetails) {
        return userRepo.findUserByEmail(userDetails.getUsername());
    }

    public boolean isTableFilled(Long clientID) {
        Optional<Client> client = clientRepo.findById(clientID);
        return !client.isEmpty();
    }

    public Optional<Client> getAllDetals(Long userID) {
        return clientRepo.findById(userID);
    }


    public boolean findeExisting(Long userID) {
       Optional<Client> client =  clientRepo.findById(userID);
       if(client != null){
           return true;
       }
       return false;
    }
}
