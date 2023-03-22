package mate.academy.springboot.datajpa.service.impl;

import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.repository.ProductRepository;
import mate.academy.springboot.datajpa.service.ProductService;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product create(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product getById(Long id) {
        return productRepository.getById(id);
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product update(Product product) {
        Product byIdProduct = getById(product.getId());
        byIdProduct.setTitle(product.getTitle());
        byIdProduct.setPrice(product.getPrice());
        byIdProduct.setCategory(product.getCategory());
        return productRepository.save(byIdProduct);
    }
}
