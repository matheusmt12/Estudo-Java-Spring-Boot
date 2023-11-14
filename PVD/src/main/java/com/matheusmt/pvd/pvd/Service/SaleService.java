package com.matheusmt.pvd.pvd.Service;

import com.matheusmt.pvd.pvd.DTO.ProductSaleDTO;
import com.matheusmt.pvd.pvd.DTO.ProductDTOInfo;
import com.matheusmt.pvd.pvd.DTO.SaleDTO;
import com.matheusmt.pvd.pvd.DTO.SaleInfoDTO;
import com.matheusmt.pvd.pvd.Exceptions.InvaledOperationException;
import com.matheusmt.pvd.pvd.Exceptions.NoItemException;
import com.matheusmt.pvd.pvd.Repository.IItemSalesRepository;
import com.matheusmt.pvd.pvd.Repository.IProductRepository;
import com.matheusmt.pvd.pvd.Repository.ISalesRepository;
import com.matheusmt.pvd.pvd.Repository.IUserRepository;
import com.matheusmt.pvd.pvd.entity.ItemSale;
import com.matheusmt.pvd.pvd.entity.Product;
import com.matheusmt.pvd.pvd.entity.Sale;
import com.matheusmt.pvd.pvd.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
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

        return SaleInfoDTO.builder()
                .username(sale.getUser().getName())
                .date(sale.getDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .products(getProoductsInfo(sale.getItemSales()))
                .price(getPrice(sale.getItemSales())).build();
    }

    private BigDecimal getPrice(List<ItemSale> itemSales) {
        if(CollectionUtils.isEmpty(itemSales)){
            throw new NoItemException("Não ha itens disponível");
        }
        BigDecimal price = BigDecimal.ZERO;

        for(ItemSale item : itemSales){
            BigDecimal total = item.getProduct().getPrice().multiply(BigDecimal.valueOf(item.getProduct().getQuantity()));
            price = price.add(total);

        }
        System.out.println("Valor final " + price);
        return  price;
    }

    private List<ProductDTOInfo> getProoductsInfo(List<ItemSale> itemSales) {
        if(CollectionUtils.isEmpty(itemSales)){
            return Collections.emptyList();
        }
        return itemSales.stream().map(
                item -> ProductDTOInfo.builder()
                    .description(item.getProduct().getDescription())
                    .quantity(item.getQuantity()).build()

        ).collect(Collectors.toList());
    }

    public SaleInfoDTO getById(Long id){
        Sale sale = iSalesRepository.findById(id).orElseThrow(() ->
                new NoItemException("Venda não encontrada"));
        return getSaleInfo(sale);

    }


    public Long save(SaleDTO sale){

        User user = iUserRepository.findById(sale.getUserid()).orElseThrow(() ->
                new NoItemException("Não ha usário cadastrado"));

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

    private List<ItemSale> getItemSale(List<ProductSaleDTO> items) {
        return items.stream().map(item ->{
            Product product = new Product();
            product = iProductRepository.getReferenceById(item.getProductid());
            ItemSale itemSale = new ItemSale();
            itemSale.setProduct(product);

            if(product.getQuantity() == 0) {
                throw new NoItemException("Acabou o estoque do produto:" + product.getDescription());
            }
            else if(product.getQuantity() < item.getQuantity()){
                throw new InvaledOperationException(String.format("A quantidade de itens dispiníveis (%s)" +
                        "é menor do que a quantidade desejada (%s)",product.getQuantity(),item.getQuantity()));
            }

            itemSale.setQuantity(item.getQuantity());
            int all = product.getQuantity() - item.getQuantity();
            product.setQuantity(all);
            iProductRepository.save(product);
            return itemSale;
        }).collect(Collectors.toList());
    }

}
