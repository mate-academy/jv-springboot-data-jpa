package mate.academy.springboot.datajpa.model;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 25)
    private String title;
    private BigDecimal price;
    @ManyToOne
    private Category category;

    @Override
    public String toString() {
        return "Product{"
                + "title='" + title + '\''
                + ", price=" + price
                + ", category=" + category
                + '}';
    }
}
