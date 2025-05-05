package mate.academy.springboot.datajpa.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "products")
public class Product {
    @Id
    private Long id;
    private String title;
    private int price;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}


