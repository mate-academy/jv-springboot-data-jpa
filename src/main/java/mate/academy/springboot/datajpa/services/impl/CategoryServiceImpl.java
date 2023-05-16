package mate.academy.springboot.datajpa.services.impl;

import lombok.RequiredArgsConstructor;
import mate.academy.springboot.datajpa.models.Category;
import mate.academy.springboot.datajpa.repositories.CategoryRepository;
import mate.academy.springboot.datajpa.services.CategoryService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Couldn't find category by id " + id));
    }

    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Category update(Category category) {
        Category newCategory = categoryRepository.findById(category.getId())
                .orElseThrow(() -> new RuntimeException("Couldn't find category by id "
                        + category.getId()));
        newCategory.setName(category.getName());
        categoryRepository.save(newCategory);
        return newCategory;
    }
}
