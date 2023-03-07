package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileUploadDTO {
    private Long projectID;
    private String fileName;
    private String fileType;
    private byte[] file;

}
