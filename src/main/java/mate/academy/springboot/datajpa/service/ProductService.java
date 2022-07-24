package mate.academy.springboot.datajpa.service;

import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.model.dto.response.ProductResponseDto;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    Product save(Product product);

    Product getById(Long id);

    void delete(Long id);

    List<Product> findAllByPriceBetween(BigDecimal from, BigDecimal to);

    List<Product> findAllByCategoryIn(List<Long> categoryIds);
}
