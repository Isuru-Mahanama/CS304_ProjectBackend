package com.example.demo.dto;

import com.example.demo.Model.Project;
import com.example.demo.Model.Subcategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ViewProjceDTO {
    private Project project;
    private Subcategory subcategories;
}
