package mate.academy.springboot.datajpa.services.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import mate.academy.springboot.datajpa.exception.NoSuchProductException;
import mate.academy.springboot.datajpa.models.Product;
import mate.academy.springboot.datajpa.repositories.ProductRepository;
import mate.academy.springboot.datajpa.repositories.specification.SpecificationManager;
import mate.academy.springboot.datajpa.services.ProductService;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final SpecificationManager<Product> productSpecificationManager;

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NoSuchProductException("Couldn't find product by id " + id));
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product update(Product product) {
        if (product != null) {
            Product newProduct = productRepository.findById(product.getId())
                    .orElseThrow(() -> new NoSuchProductException("Couldn't find product by id "
                            + product.getId()));
            newProduct.setCategory(product.getCategory());
            newProduct.setTitle(product.getTitle());
            newProduct.setPrice(product.getPrice());
            productRepository.save(newProduct);
            return newProduct;
        }
        throw new NoSuchProductException("Product can't be null");
    }

    @Override
    public List<Product> findAllByPriceBetween(@RequestParam BigDecimal from,
                                               @RequestParam BigDecimal to) {
        return productRepository.findAllByPriceBetween(from, to);
    }

    @Override
    public List<Product> findAllByCategories(List<String> categories) {
        return productRepository.findAllByCategories(categories);
    }

    @Override
    public List<Product> findAll(Map<String, String> params) {
        Specification<Product> specification = null;
        for (Map.Entry<String, String> entry : params.entrySet()) {
            Specification<Product> sp = productSpecificationManager.get(entry.getKey(),
                    entry.getValue().split(","));
            specification = specification == null
                    ? Specification.where(sp) : specification.and(sp);
        }
        return productRepository.findAll(specification);
    }
}
