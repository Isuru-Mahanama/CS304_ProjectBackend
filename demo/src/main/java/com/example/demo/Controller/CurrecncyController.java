package com.example.demo.Controller;

import com.example.demo.Model.CurrencyType;
import com.example.demo.Service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value ="api/v1/user")
@CrossOrigin(origins ="*", allowedHeaders = "*")
public class CurrecncyController {
    @Autowired
    private CurrencyService currencyService;
    @GetMapping("/getAllCurrencyTypes")
    public List<CurrencyType> getAllCurrecyTypes(){
        return currencyService.getAllCurencyTYpes();
    }
}
