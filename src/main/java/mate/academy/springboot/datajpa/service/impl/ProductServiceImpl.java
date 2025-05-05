package mate.academy.springboot.datajpa.service.impl;

import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.repository.ProductRepository;
import mate.academy.springboot.datajpa.repository.specification.ProductSpecificationManager;
import mate.academy.springboot.datajpa.service.ProductService;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductSpecificationManager productSpecificationManager;

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product get(Long id) {
        return productRepository.getById(id);
    }

    @Override
    public List<Product> findAll(Map<String, String> params) {
        Specification<Product> totalSpecification = null;
        for (Map.Entry<String, String> entry : params.entrySet()) {
            Specification<Product> localSpecification =
                    productSpecificationManager.get(entry.getKey(),
                            entry.getValue().split(","));
            totalSpecification = totalSpecification == null
                    ? Specification.where(localSpecification)
                    : totalSpecification.and(localSpecification);
        }
        return productRepository.findAll(totalSpecification);
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}
