package mate.academy.springboot.datajpa.service.impl;

import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.repository.CategoryRepository;
import mate.academy.springboot.datajpa.service.AbstractService;
import mate.academy.springboot.datajpa.service.CategoryService;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends AbstractService<Category, Long>
        implements CategoryService {
    public CategoryServiceImpl(CategoryRepository repository) {
        super(repository);
    }
}
