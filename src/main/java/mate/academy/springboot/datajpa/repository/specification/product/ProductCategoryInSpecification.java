package mate.academy.springboot.datajpa.repository.specification.product;

import javax.persistence.criteria.CriteriaBuilder;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.repository.specification.SpecificationProvider;
import mate.academy.springboot.datajpa.service.CategoryService;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class ProductCategoryInSpecification implements SpecificationProvider<Product> {
    private static final String FILTER_KEY = "categoryIn";
    private static final String FIELD_NAME = "category";
    private final CategoryService categoryService;

    public ProductCategoryInSpecification(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public String getFilterKey() {
        return FILTER_KEY;
    }

    @Override
    public Specification<Product> getSpecification(String categories) {
        return (root, query, cb) -> {
            CriteriaBuilder.In<Category> predicate = cb.in(root.get((FIELD_NAME)));
            predicate.value(categoryService.findByName(categories));
            return cb.and(predicate, predicate);
        };
    }
}
