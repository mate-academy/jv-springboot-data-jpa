package mate.academy.springboot.datajpa.service;

import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category add(Category category) {
        return categoryRepository.save(category);
    }

    public Category get(Long id) {
        return categoryRepository.getById(id);
    }

    public void delete(Long id) {
        categoryRepository.delete(categoryRepository.getById(id));
    }

    public void update(Category category) {
        Category byId = categoryRepository.getById(category.getId());
        byId.setName(category.getName());
        categoryRepository.save(byId);
    }
}
