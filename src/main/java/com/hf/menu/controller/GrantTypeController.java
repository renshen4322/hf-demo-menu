package com.hf.menu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.hf.menu.service.QueryGrantTypeService;

@RestController
public class GrantTypeController {
    @Autowired
    private QueryGrantTypeService queryGrantTypeService;

    @GetMapping("/grantType")
    public String test(String resourceName){
        return queryGrantTypeService.getResult(resourceName);
    }
}
