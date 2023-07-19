package mate.academy.springboot.datajpa.service.impl;

import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.repository.CategoryRepository;
import mate.academy.springboot.datajpa.service.AbstractCrudService;
import mate.academy.springboot.datajpa.service.CategoryService;
import org.springframework.stereotype.Service;

@Service
public class DefaultCategoryService
        extends AbstractCrudService<Category, Long>
        implements CategoryService {
    public DefaultCategoryService(CategoryRepository categoryRepository) {
        super(categoryRepository, Category.class);
    }
}
