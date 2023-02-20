package mate.academy.springboot.datajpa.service;

import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category add(Category category) {
        return categoryRepository.save(category);
    }

    @Transactional
    @Override
    public void update(Category category) {
        categoryRepository.updateCategory(category.getId(), category.getName());
    }

    @Override
    public Category get(Long id) {
        return categoryRepository.getReferenceById(id);
    }

    @Override
    public void remove(Long id) {
        categoryRepository.deleteById(id);
    }
}
