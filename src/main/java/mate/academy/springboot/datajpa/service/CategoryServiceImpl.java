package mate.academy.springboot.datajpa.service;

import java.util.Optional;
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

    @Override
    public Category create(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Category not found! Params: id = " + id)
        );
    }

    @Override
    public Category deleteById(Long id) {
       Category category = categoryRepository.getById(id);
        categoryRepository.delete(category);
        return category;
    }

    @Override
    public Category update(Long id, Category category) {
       category.setId(id);
        return categoryRepository.save(category);
    }
}
