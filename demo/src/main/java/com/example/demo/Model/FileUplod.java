package com.example.demo.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class FileUplod {
    @Id
    private Long projectID;
    private String fileName;
    private String filePath;
    private String imageName;
    private String imagePath;

}
