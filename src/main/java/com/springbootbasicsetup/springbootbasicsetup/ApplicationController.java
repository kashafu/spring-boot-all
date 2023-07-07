package com.springbootbasicsetup.springbootbasicsetup;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApplicationController {
    @GetMapping("/")
    public String hi() {
        return "Hi Kashaf";
    }
}
