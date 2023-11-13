package com.matheusmt.pvd.pvd.Controller;

import com.matheusmt.pvd.pvd.DTO.ProductDTO;
import com.matheusmt.pvd.pvd.Repository.IProductRepository;
import com.matheusmt.pvd.pvd.entity.Product;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {

    private IProductRepository iProductRepository;
    private ModelMapper mapper;
    public ProductController(@Autowired IProductRepository iProductRepository) {
        this.iProductRepository = iProductRepository;
        this.mapper = new ModelMapper();
    }

    @GetMapping()
    public ResponseEntity getAll(){
        return new ResponseEntity<>(iProductRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity post(@RequestBody ProductDTO product){
        try{
            return new ResponseEntity<>(iProductRepository.save(mapper.map(product,Product.class)), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping()
    public ResponseEntity put(@RequestBody ProductDTO product){

        try {
            Product prod = mapper.map(product,Product.class);
            Optional<Product> optional = iProductRepository.findById(prod.getId());
            if (optional.isPresent()){

                return new ResponseEntity<>(iProductRepository.save(prod),HttpStatus.OK);
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
