package mate.academy.springboot.datajpa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Data
@Entity
@Accessors(chain = true)
@Table(name = "category")
@SQLDelete(sql = "UPDATE category SET deleted = true WHERE id = ?")
@Where(clause = "deleted=false")
public class Category extends BaseEntity {

    @Column(name = "name")
    private String name;
}
