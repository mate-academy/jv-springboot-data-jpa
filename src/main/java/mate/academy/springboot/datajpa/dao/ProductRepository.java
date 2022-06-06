package mate.academy.springboot.datajpa.dao;

import mate.academy.springboot.datajpa.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(value = "update Product p set where p.id = :id")
    Product update(Product product);
}
