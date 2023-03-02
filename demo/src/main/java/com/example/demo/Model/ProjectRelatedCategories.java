package com.example.demo.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Table
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProjectRelatedCategories {

    @EmbeddedId
    private ProjectCategoryId id;

    public static class ProjectCategoryId implements Serializable {
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "project_id")
        private Project projectCategoryID;
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "category_id")
        private ProjectCategories categoryID;
        private Long subCategoryID;
    }

}
