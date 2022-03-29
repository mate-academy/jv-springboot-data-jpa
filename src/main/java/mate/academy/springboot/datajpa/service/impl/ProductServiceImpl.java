package mate.academy.springboot.datajpa.service.impl;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.repository.ProductRepository;
import mate.academy.springboot.datajpa.repository.specification.ProductSpecificationManager;
import mate.academy.springboot.datajpa.service.CategoryService;
import mate.academy.springboot.datajpa.service.ProductRatingSort;
import mate.academy.springboot.datajpa.service.ProductService;
import mate.academy.springboot.datajpa.util.SystemException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductRatingSort productRatingSort;
    private final ProductSpecificationManager productSpecificationManager;
    private final CategoryService categoryService;

    public ProductServiceImpl(ProductRepository productRepository,
                              ProductRatingSort productRatingSort,
                              ProductSpecificationManager productSpecificationManager,
                              CategoryService categoryService) {
        this.productRepository = productRepository;
        this.productRatingSort = productRatingSort;
        this.productSpecificationManager = productSpecificationManager;
        this.categoryService = categoryService;
    }

    @Override
    public Product save(Product product) {
        if (product.getCategory() != null) {
            String categoryName = StringUtils.capitalize(product.getCategory().getName());
            checkIfCategoryAlreadyExist(categoryName);
            product.setCategory(categoryService.getByName(categoryName));
        }

        return productRepository.save(product);
    }

    @Override
    public Product getById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new SystemException("No product found by id " + id));
    }

    @Override
    public void deleteById(Long id) {
        try {
            productRepository.deleteById(id);
        } catch (RuntimeException e) {
            throw new SystemException("No product found by id " + id);
        }
    }

    @Override
    public Product update(Product product) {
        String categoryName = product.getCategory().getName();
        checkIfCategoryAlreadyExist(categoryName);
        product.setCategory(categoryService.getByName(categoryName));
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAll() {
        List<Product> products = productRepository.findAll();
        products.sort(productRatingSort);
        return products;
    }

    @Override
    public List<Product> findAll(Map<String, String> parameters) {
        Specification<Product> specification = null;
        for (Map.Entry<String, String> entry: parameters.entrySet()) {
            Specification<Product> sp = productSpecificationManager
                    .get(entry.getKey(), entry.getValue().split(","));
            specification = specification == null ? Specification.where(sp) : specification.and(sp);
        }

        return productRepository.findAll(specification);
    }

    public void checkIfCategoryAlreadyExist(String categoryName) {
        try {
            categoryService.getByName(categoryName);
        } catch (NoSuchElementException e) {
            categoryService.save(new Category(categoryName));
        }
    }
}
