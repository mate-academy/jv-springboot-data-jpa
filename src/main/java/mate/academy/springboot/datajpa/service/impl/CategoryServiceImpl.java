package mate.academy.springboot.datajpa.service.impl;

import java.util.NoSuchElementException;
import java.util.Optional;
import lombok.AllArgsConstructor;
import mate.academy.springboot.datajpa.dao.CategoryRepository;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.service.CategoryService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category get(Long id) {
        return categoryRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("Category with id " + id + " hasn't found"));
    }

    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Category update(Long id, Category category) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if (categoryOptional.isEmpty()) {
            throw new NoSuchElementException("Category with id " + id + " hasn't found");
        }
        Category categoryByName = categoryOptional.get();
        categoryByName.setName(category.getName());
        return categoryRepository.save(categoryByName);
    }
}
