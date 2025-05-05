package mate.academy.springboot.datajpa.service;

import java.util.List;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product getById(Long id) {
        return productRepository.getById(id);
    }

    @Override
    public Product deleteById(Long id) {
        Product product = productRepository.getById(id);
        productRepository.deleteById(id);
        return product;
    }

    @Override
    public Product update(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllByPriceBetween(int from, int to) {
        return productRepository.findAllByPriceBetween(from, to);
    }

    @Override
    public List<Product> getAllByCategoriesIn(List<Long> categoriesIds) {
        return productRepository.findAllByCategoryIn(categoriesIds);
    }
}
