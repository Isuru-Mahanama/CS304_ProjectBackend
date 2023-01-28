package com.example.demo.Service;

import com.example.demo.Model.*;
import com.example.demo.dto.ClientDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.repo.ClientRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ClientService {

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
}
