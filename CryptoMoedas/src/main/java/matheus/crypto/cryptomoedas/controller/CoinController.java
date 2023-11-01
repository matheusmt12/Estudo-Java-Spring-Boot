package matheus.crypto.cryptomoedas.controller;

import matheus.crypto.cryptomoedas.Entity.Coin;
import matheus.crypto.cryptomoedas.Repository.CoinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

@RestController
@RequestMapping("/coin")
public class CoinController {

    @Autowired
    private CoinRepository coinRepository;

    @PostMapping()
    public ResponseEntity post(@RequestBody Coin coin){
        try{
            coin.setDateTime(new Timestamp(System.currentTimeMillis()));
            return  new ResponseEntity<>(coinRepository.insert(coin), HttpStatus.CREATED);
        }
        catch (Exception e){
            System.out.println("Erro: " + e.getMessage());
            return  new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping()
    public ResponseEntity get(){
        try {
            return new ResponseEntity<>(coinRepository.getAll(),HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{name}")
    public ResponseEntity getName(@PathVariable String name){
        try {
            return new ResponseEntity<>(coinRepository.getName(name),HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable int id){
        int response = 0;
        try {
            response = coinRepository.delete(id);
            return new ResponseEntity<>(response,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping()
    public ResponseEntity update(@RequestBody Coin coin){
        try{
            coin.setDateTime(new Timestamp(System.currentTimeMillis()));
            return new ResponseEntity<>(coinRepository.update(coin),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
