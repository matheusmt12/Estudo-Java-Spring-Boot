package com.matheusmt.pvd.pvd.Controller;

import com.matheusmt.pvd.pvd.DTO.SaleDTO;
import com.matheusmt.pvd.pvd.Repository.ISalesRepository;
import com.matheusmt.pvd.pvd.Service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sale")

public class SaleController {

    private SaleService saleService;
    private ISalesRepository iSalesRepository;
    public SaleController(@Autowired SaleService saleService, ISalesRepository iSalesRepository){
        this.saleService = saleService;
        this.iSalesRepository = iSalesRepository;
    }

    @PostMapping
    public ResponseEntity post(@RequestBody SaleDTO saleDTO){

      try{
          Long id = saleService.save(saleDTO);
          return new ResponseEntity<>("Venda concluida com sucesso " + id, HttpStatus.OK);
      }catch (Exception e)
      {
          return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

    @GetMapping
    public ResponseEntity get(){
        try {
            return new ResponseEntity<>(iSalesRepository.findAll(),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Erro do server", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
