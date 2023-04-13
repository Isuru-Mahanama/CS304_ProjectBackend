package com.example.demo.dto;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public
class CreatePaymentDTO {
    @SerializedName("items")
    Object[] items;
    private String amount;
    private String projectID;
    private String clientSecret;

    public Object[] getItems() {
        return items;
    }
}