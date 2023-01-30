package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LanguageDTO {
    private Long userLanguageID;
    private String language;
    private String languageLevel;

}
