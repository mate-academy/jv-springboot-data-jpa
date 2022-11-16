package mate.academy.springboot.datajpa.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "categories")
public class Category {
    @Id
    private Long id;
    private String name;
}