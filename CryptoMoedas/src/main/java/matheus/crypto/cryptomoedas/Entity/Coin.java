package matheus.crypto.cryptomoedas.Entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Timestamp;


@Entity
@Table(name = "coin")
public class Coin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "DATETIME")
    private Timestamp dateTime;
    @Column(name = "PRICE")
    private BigDecimal price;
    @Column(name = "QUANTITY")
    private BigDecimal quantity;


    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Timestamp getDateTime() {
        return dateTime;
    }

    public void setDateTime(Timestamp dataTime) {
        this.dateTime = dataTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
