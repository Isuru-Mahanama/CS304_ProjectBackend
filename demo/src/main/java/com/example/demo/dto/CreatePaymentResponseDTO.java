package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public
class CreatePaymentResponseDTO {
    private String clientSecret;
    public CreatePaymentResponseDTO(String clientSecret) {
        this.clientSecret = clientSecret;
    }
}

