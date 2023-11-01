package com.matheusmt.pvd.pvd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
@SpringBootApplication
public class PvdApplication {

    public static void main(String[] args) {
        SpringApplication.run(PvdApplication.class, args);
    }


    @GetMapping("/teste")
    public String teste(){
        return "hello word";
    }


}
