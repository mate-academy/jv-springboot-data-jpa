package mate.academy.springboot.datajpa.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.repository.CategoryRepository;
import mate.academy.springboot.datajpa.service.CategoryService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public Category add(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category get(Long id) {
        return categoryRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Category with id: " + id + " not found"));
    }

    @Override
    public Category update(Category category) {
        return categoryRepository.saveAndFlush(category);
    }

    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }
}
