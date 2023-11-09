package com.matheusmt.pvd.pvd.Service;

import com.matheusmt.pvd.pvd.DTO.ProductDTO;
import com.matheusmt.pvd.pvd.DTO.ProductDTOInfo;
import com.matheusmt.pvd.pvd.DTO.SaleDTO;
import com.matheusmt.pvd.pvd.DTO.SaleInfoDTO;
import com.matheusmt.pvd.pvd.Repository.IItemSalesRepository;
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
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SaleService {

    private final IUserRepository iUserRepository;
    private final ISalesRepository iSalesRepository;
    private final IProductRepository iProductRepository;
    private final IItemSalesRepository iItemSalesRepository;




    public List<SaleInfoDTO> findAll(){
        return iSalesRepository.findAll().stream().map(sale -> getSaleInfo(sale)).collect(Collectors.toList());
    }

    private SaleInfoDTO getSaleInfo(Sale sale) {
        SaleInfoDTO saleInfoDTO = new SaleInfoDTO();

        saleInfoDTO.setUsername(sale.getUser().getName());
        saleInfoDTO.setDate(sale.getDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        saleInfoDTO.setProducts(getProoductsInfo(sale.getItemSales()));

        return saleInfoDTO;

    }

    private List<ProductDTOInfo> getProoductsInfo(List<ItemSale> itemSales) {
        return itemSales.stream().map(item -> {
            ProductDTOInfo productDTOInfo = new ProductDTOInfo();

            productDTOInfo.setDescription(item.getProduct().getDescription());
            productDTOInfo.setQuantity(item.getQuantity());
            return productDTOInfo;

        }).collect(Collectors.toList());
    }

    public SaleInfoDTO getById(Long id){
        Sale sale = iSalesRepository.findById(id).get();
        return getSaleInfo(sale);

    }


    public Long save(SaleDTO sale){

        User user = iUserRepository.findById(sale.getUserid()).get();

        Sale newSale = new Sale();

        newSale.setUser(user);
        newSale.setDate(LocalDate.now());
        newSale = iSalesRepository.save(newSale);

        List<ItemSale> item = getItemSale(sale.getItem());

        saveItemSale(item,newSale);

        return newSale.getId();
    }

    private void saveItemSale(List<ItemSale> items, Sale newSale) {
        for(ItemSale item : items){
            item.setSale(newSale);
            iItemSalesRepository.save(item);
        }
    }

    private List<ItemSale> getItemSale(List<ProductDTO> items) {
        return items.stream().map(item ->{
            Product product = new Product();
            product = iProductRepository.getReferenceById(item.getProductid());
            ItemSale itemSale = new ItemSale();
            itemSale.setProduct(product);

            if(product.getQuantity() == 0 || product.getQuantity() < item.getQuantity()) {
                throw new IllegalArgumentException();
            }

            itemSale.setQuantity(item.getQuantity());
            int all = product.getQuantity() - item.getQuantity();
            product.setQuantity(all);
            iProductRepository.save(product);
            return itemSale;
        }).collect(Collectors.toList());
    }

}
