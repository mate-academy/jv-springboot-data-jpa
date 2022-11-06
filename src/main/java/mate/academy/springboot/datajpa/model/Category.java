package mate.academy.springboot.datajpa.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "categorys")
public class Category {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
}
