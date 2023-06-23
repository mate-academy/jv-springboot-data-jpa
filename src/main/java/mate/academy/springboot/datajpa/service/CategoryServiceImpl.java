package mate.academy.springboot.datajpa.service;

import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.repository.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category saveCategory(Category category) {

        return categoryRepository.save(category);
    }

    @Override
    public Category getCategoryById(Long id) {

        return categoryRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Can't get category by id:" + id));
    }

    @Override
    public void deleteCategoryById(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Category updateCategory(Category category) {

        return categoryRepository.save(category);
    }
}
