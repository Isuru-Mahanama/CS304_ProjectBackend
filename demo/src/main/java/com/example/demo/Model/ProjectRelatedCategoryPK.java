package com.example.demo.Model;

import jakarta.persistence.IdClass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode

public class ProjectRelatedCategoryPK implements Serializable {

     private Long project;
    private Long esubCategoryID;
}
