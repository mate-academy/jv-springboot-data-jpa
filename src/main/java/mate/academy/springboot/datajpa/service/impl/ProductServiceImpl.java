package mate.academy.springboot.datajpa.service.impl;

import java.math.BigDecimal;
import java.util.List;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.repository.ProductRepository;
import mate.academy.springboot.datajpa.service.AbstractService;
import mate.academy.springboot.datajpa.service.ProductService;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl extends AbstractService<Product, Long> implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository repository) {
        super(repository);
        this.productRepository = repository;
    }

    @Override
    public List<Product> findAllByPriceBetween(BigDecimal from, BigDecimal to) {
        return productRepository.findAllByPriceBetween(from, to);
    }

    @Override
    public List<Product> findAllByCategoryNameIn(List<String> categories) {
        return productRepository.findAllByCategoryNameIn(categories);
    }
}
