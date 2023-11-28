package com.matheusmt.pvd.pvd.Controller;

import com.matheusmt.pvd.pvd.DTO.LoginDTO;
import com.matheusmt.pvd.pvd.DTO.TokenDTO;
import com.matheusmt.pvd.pvd.Security.CustonUserDetalheService;
import com.matheusmt.pvd.pvd.Security.JwtService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private CustonUserDetalheService custonUserDetalheService;

    @Autowired
    private JwtService jwtService;

    @Value("${security.jwt.expiration}")
    private String expiration;
    @PostMapping
    public ResponseEntity post(@Valid @RequestBody LoginDTO loginData){
        try{
            custonUserDetalheService.verifyUserCredentials(loginData);
            String token = jwtService.generateToken(loginData.getUsername());

            return new ResponseEntity<>(new TokenDTO(token,expiration),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
