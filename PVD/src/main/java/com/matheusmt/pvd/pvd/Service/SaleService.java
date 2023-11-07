package com.matheusmt.pvd.pvd.Service;

import com.matheusmt.pvd.pvd.DTO.ProductDTO;
import com.matheusmt.pvd.pvd.DTO.SaleDTO;
import com.matheusmt.pvd.pvd.Repository.IItemSales;
import com.matheusmt.pvd.pvd.Repository.IProductRepository;
import com.matheusmt.pvd.pvd.Repository.ISalesRepository;
import com.matheusmt.pvd.pvd.Repository.IUserRepository;
import com.matheusmt.pvd.pvd.entity.ItemSale;
import com.matheusmt.pvd.pvd.entity.Product;
import com.matheusmt.pvd.pvd.entity.Sale;
import com.matheusmt.pvd.pvd.entity.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SaleService {

    private final IUserRepository iUserRepository;
    private final IProductRepository iProductRepository;
    private final ISalesRepository iSalesRepository;
    private final IItemSales itemSales;


    @Transactional
    public Long save(SaleDTO saleDTO){

        User user = iUserRepository.findById(saleDTO.getUserid()).get();

        Sale newSale = new Sale();

        newSale.setUser(user);
        newSale.setDate(LocalDate.now());

        List<ItemSale> items = getItemSale(saleDTO.getItem()); 
        newSale = iSalesRepository.save(newSale);


        saveItemSale(items,newSale);

        return newSale.getId();
    }


    private void saveItemSale(List<ItemSale> items, Sale newSale) {
        for(ItemSale item : items){
            item.setSale(newSale);
            itemSales.save(item);

        }
    }


    private List<ItemSale> getItemSale(List<ProductDTO> products){

        return products.stream().map(item -> {
            Product product = iProductRepository.getReferenceById(item.getProductid());

            ItemSale itemSale = new ItemSale();
            itemSale.setProduct(product);
            itemSale.setQuantity(item.getQuantity());
            return itemSale;
        }).collect(Collectors.toList());
    }
}
