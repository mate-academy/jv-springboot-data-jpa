package mate.academy.springboot.datajpa.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private BigDecimal price;
    @OneToOne
    private Category category;

    @Override
    public String toString() {
        return "Product={id=" + id
                + "/'title'=" + title
                + "/price=" + price
                + "/" + category
                + "}";
    }
}
