package mate.academy.springboot.datajpa.service.impl;

import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.repository.ProductRepository;
import mate.academy.springboot.datajpa.service.ProductService;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product getById(Long id) {
        return productRepository.getById(id);
    }

    @Override
    public List<Product> getAllProductByPriceIsBetween(BigDecimal startPrice, BigDecimal endPrice) {
        return productRepository.getAllByPriceIsBetween(startPrice, endPrice);
    }

    @Override
    public List<Product> getAllProductByCategoriesId(List<Long> categoriesId) {
        return productRepository.getAllByCategoryIdIsIn(categoriesId);
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}
