package com.example.demo.Service;

import com.example.demo.Model.PaymentDetails;
import com.example.demo.Model.User;
import com.example.demo.dto.UserDTO;
import com.example.demo.repo.PaymentRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PaymentService {
    @Autowired
    private PaymentRepo paymentRepo;
    @Autowired
    private ModelMapper modelMapper;

    public PaymentDetails savePaymentDetails(UserDTO user, String payment,String projectID) {
        PaymentDetails paymentDetails = new PaymentDetails();
        paymentDetails.setFk_userID(modelMapper.map(user, User.class));
        paymentDetails.setAmount(payment);
        paymentDetails.setProjectID(projectID);
        return paymentRepo.save(paymentDetails);
    }

    public void updatePaymentStatus() {
    }
}
