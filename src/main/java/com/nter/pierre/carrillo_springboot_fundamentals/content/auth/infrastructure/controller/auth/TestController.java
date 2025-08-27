package com.nter.pierre.carrillo_springboot_fundamentals.content.auth.infrastructure.controller.auth;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/method")
public class TestController {
    //Estos Endpoints son mis endpoints dentro de Entity
    @GetMapping("/get")
    public String callGet(){
        return "Method Called With GET";
    }

    @PostMapping("/post")
    public String callPost(){
        return "Method Called With POST";
    }

    @PutMapping("/put")
    public String callPut(){
        return "Method Called With PUT";
    }

    @DeleteMapping("/delete")
    public String callDelete(){
        return "Method Called With DELETE";
    }
}