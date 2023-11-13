package com.matheusmt.pvd.pvd.Controller;


import com.matheusmt.pvd.pvd.DTO.ResponseDTO;
import com.matheusmt.pvd.pvd.DTO.UserDTO;
import com.matheusmt.pvd.pvd.Repository.IUserRepository;
import com.matheusmt.pvd.pvd.Service.UserService;
import com.matheusmt.pvd.pvd.entity.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    public UserController(@Autowired UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity getAll() {
        try {
            return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity insert(@Valid @RequestBody UserDTO user) {
        try {
            user.setEnabled(true);
            return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping()
    public ResponseEntity update(@Valid @RequestBody UserDTO user) {
        try {
            return new ResponseEntity<>(userService.update(user),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable Long id) {

        try{
            UserDTO userDTO = userService.findById(id);
            userService.deleteById(id);
            return new ResponseEntity(new ResponseDTO("Usuario removido com sucesso¹"),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
