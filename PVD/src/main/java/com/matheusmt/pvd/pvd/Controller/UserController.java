package com.matheusmt.pvd.pvd.Controller;


import com.matheusmt.pvd.pvd.Repository.IUserRepository;
import com.matheusmt.pvd.pvd.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    private IUserRepository iUserRepository;

    public UserController(@Autowired IUserRepository iUserRepository) {
        this.iUserRepository = iUserRepository;
    }

    @GetMapping
    public ResponseEntity getAll() {
        try {
            return new ResponseEntity<>(iUserRepository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity insert(@RequestBody User user) {
        try {
            user.setEnabled(true);
            return new ResponseEntity<>(iUserRepository.save(user), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping()
    public ResponseEntity update(@RequestBody User user) {
        try {
            Optional<User> opt = iUserRepository.findById(user.getId());
            if (opt.isPresent()) {
                return new ResponseEntity<>(user, HttpStatus.OK);
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable Long id) {

        Optional<User> find = iUserRepository.findById(id);
        try {
            if (find.isPresent()) {
                iUserRepository.deleteById(id);
                return new ResponseEntity<>("Removido com sucesso", HttpStatus.OK);
            }
            return new ResponseEntity<>("Não foi possivel encontrar", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
