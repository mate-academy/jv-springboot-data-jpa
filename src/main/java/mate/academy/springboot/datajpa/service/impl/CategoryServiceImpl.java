package mate.academy.springboot.datajpa.service.impl;

import lombok.AllArgsConstructor;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.repository.CategoryRepository;
import mate.academy.springboot.datajpa.service.CategoryService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id).orElseThrow(()
                -> new RuntimeException("Can`t find category with id: " + id));
    }

    @Override
    public Category update(Long id, Category category) {
        findById(id);
        category.setId(id);
        return categoryRepository.save(category);
    }

    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }
}
