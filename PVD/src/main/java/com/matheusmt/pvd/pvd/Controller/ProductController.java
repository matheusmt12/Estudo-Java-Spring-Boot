package com.matheusmt.pvd.pvd.Controller;

import com.matheusmt.pvd.pvd.Repository.IProductRepository;
import com.matheusmt.pvd.pvd.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {

    private IProductRepository iProductRepository;

    public ProductController(@Autowired IProductRepository iProductRepository) {
        this.iProductRepository = iProductRepository;
    }

    @GetMapping()
    public ResponseEntity getAll(){
        try {
            return new ResponseEntity<>(iProductRepository.findAll(),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping()
    public ResponseEntity post(@RequestBody Product product){
        try{
            return new ResponseEntity<>(iProductRepository.save(product), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping()
    public ResponseEntity put(@RequestBody Product product){

        try {
            Optional<Product> optional = iProductRepository.findById(product.getId());
            if (optional.isPresent()){
                return new ResponseEntity<>(product,HttpStatus.OK);
            }
            return ResponseEntity.notFound().build();

        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable Long id){
        try {
            Optional<Product> optional = iProductRepository.findById(id);
            if(optional.isPresent()){
                iProductRepository.deleteById(id);
                return new ResponseEntity<>("Deletado com sucesso",HttpStatus.OK);
            }
            return new ResponseEntity<>("product not find",HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
