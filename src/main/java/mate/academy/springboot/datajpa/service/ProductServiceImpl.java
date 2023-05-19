package mate.academy.springboot.datajpa.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.repository.ProductRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product get(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cannot find product by id " + id));
    }

    @Override
    public void delete(Product product) {
        productRepository.delete(product);
    }

    @Override
    public List<Product> findProductsByPriceBetween(Long from, Long to) {
        return productRepository.findProductsByPriceBetween(from, to);
    }

    @Override
    public List<Product> findProductsByCategoryName(List<String> categories) {
        return productRepository.findProductsByCategoryNameIn(categories);
    }
}
