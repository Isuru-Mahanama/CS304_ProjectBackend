package com.example.demo.ErrorHandling;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Response {

        private boolean success;
        private String message;

        public boolean isSuccess() {
              return  success =true;
        }

}
