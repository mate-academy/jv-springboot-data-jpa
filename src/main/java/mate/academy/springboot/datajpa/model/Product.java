package mate.academy.springboot.datajpa.model;

import java.math.BigDecimal;
import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private BigDecimal price;
    @ManyToOne
    private Category category;
}
