package mate.academy.springboot.datajpa.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Data
@Entity
@Accessors(chain = true)
@Table(name = "product")
@SQLDelete(sql = "UPDATE product SET deleted = true WHERE id = ?")
@Where(clause = "deleted=false")
public class Product extends BaseEntity {

    @Column(name = "title")
    private String title;

    @Column(name = "price")
    private Integer price;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
}
