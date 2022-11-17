package Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "purchase")
@Getter
@Setter
@NoArgsConstructor
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "date")
    private java.sql.Date date;

    public Purchase(Customer customer, Product product, java.sql.Date date) {
        this.customer = customer;
        this.product = product;
        this.date = date;
    }

    @Override
    public String toString() {
        return String.format("Purchase[id = %d, product = %s, date = %s]", id, product, date.toString());
    }
}
