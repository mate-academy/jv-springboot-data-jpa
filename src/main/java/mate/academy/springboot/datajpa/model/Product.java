package mate.academy.springboot.datajpa.model;

import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Product {
    @Id
    private Long id;
    private String title;
    private BigDecimal price;
    @ManyToOne
    private Category category;
}
