package mate.academy.springboot.datajpa.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.repository.CategoryRepository;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category getById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Can't find category by id: " + id));
    }

    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Category update(Category category) {
        categoryRepository.findById(category.getId()).orElseThrow(() ->
                new EntityNotFoundException("Can't find category by id: " + category.getId()));
        return category;
    }
}
