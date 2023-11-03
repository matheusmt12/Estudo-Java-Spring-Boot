package com.matheusmt.pvd.pvd;

import com.matheusmt.pvd.pvd.entity.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
public class PvdApplication {

    public static void main(String[] args) {
        SpringApplication.run(PvdApplication.class, args);
    }


    public void teste(){
        User u = new User();


    }


}
