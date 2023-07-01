package com.avadamedia.USAINUA.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SwaggerController {
    @GetMapping("/")
    public String swagger(){
        return "redirect:/swagger-ui/index.html";
    }
}
