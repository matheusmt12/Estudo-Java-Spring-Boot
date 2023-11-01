package matheus.crypto.cryptomoedas.Repository;


import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import matheus.crypto.cryptomoedas.DTO.CoinDTO;
import matheus.crypto.cryptomoedas.Entity.Coin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@EnableAutoConfiguration
public class CoinRepository {
    @Autowired
    EntityManager entityManager;


    public CoinRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Transactional
    public Coin insert (Coin coin){
        entityManager.persist(coin);
        return coin;
    }

    @Transactional
    public Coin update(Coin coin){
        entityManager.merge(coin);
        return coin;
    }

    public List<CoinDTO> getAll(){
       String jpql = "select new matheus.crypto.cryptomoedas.DTO.CoinDTO(c.name, sum(c.quantity))" +
               " from Coin c group by c.name";
        TypedQuery<CoinDTO> query=  entityManager.createQuery(jpql, CoinDTO.class);

        return query.getResultList();
    }


    public List<Coin> getName(String name){
        String jpql = "select c from Coin c where c.name like :name";
        TypedQuery<Coin> query = entityManager.createQuery(jpql, Coin.class);
        query.setParameter("name" ,"%" + name + "%");
        return query.getResultList();
    }

    @Transactional
    public int delete(int id){

       Coin coin =  entityManager.find(Coin.class,id);
        if (coin == null)
            throw  new RuntimeException();

        entityManager.remove(coin);
        return coin.getId();
    }

}
