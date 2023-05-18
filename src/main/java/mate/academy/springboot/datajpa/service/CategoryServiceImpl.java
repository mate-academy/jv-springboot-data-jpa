package mate.academy.springboot.datajpa.service;

import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category create(Category category) {
        return categoryRepository.save(category);
    }

    @Transactional
    @Override
    public Category get(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cannot find category by id " + id));
    }

    @Override
    public boolean delete(Category category) {
        categoryRepository.delete(category);
        return true;
    }

    @Override
    public Category update(Category category) {
        categoryRepository.update(
                category.getId(),
                category.getName()
        );
        return category;
    }
}
