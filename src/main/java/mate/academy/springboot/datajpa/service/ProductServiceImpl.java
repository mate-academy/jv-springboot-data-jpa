package mate.academy.springboot.datajpa.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import mate.academy.springboot.datajpa.model.Category;
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
    public Product add(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product getById(Long id) {
        return productRepository.getById(id);
    }

    @Override
    public void delete(Long id) {
        productRepository.delete(productRepository.getById(id));
    }

    @Override
    public void update(Product product) {
        Product byId = productRepository.getById(product.getId());
        byId.setTitle(product.getTitle());
        byId.setCategory(product.getCategory());
        byId.setPrice(product.getPrice());
        productRepository.save(byId);
    }

    @Override
    public List<Product> getAllBetween(BigDecimal from, BigDecimal to) {
        return productRepository.getAllByPriceBetween(from, to);
    }

    @Override
    public List<Product> getAllByCategory(List<Category> categories) {
        return categories.stream()
                .map(c -> productRepository.getAllByCategory(c))
                .flatMap(p -> p.stream())
                .distinct()
                .collect(Collectors.toList());
    }
}
