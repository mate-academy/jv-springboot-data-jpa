package mate.academy.springboot.datajpa.repository;

import mate.academy.springboot.datajpa.model.Product;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends GeneralRepository<Product, Long> {

}
