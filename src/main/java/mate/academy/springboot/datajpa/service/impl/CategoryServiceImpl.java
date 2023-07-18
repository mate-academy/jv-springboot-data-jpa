package mate.academy.springboot.datajpa.service.impl;

import java.util.NoSuchElementException;
import lombok.AllArgsConstructor;
import mate.academy.springboot.datajpa.dto.request.CategoryRequestDto;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.repository.CategoryRepository;
import mate.academy.springboot.datajpa.service.CategoryService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public Category update(Long id, Category category) {
        if (categoryRepository.existsById(id)) {
            category.setId(id);
            return categoryRepository.save(category);
        } else {
            throw new NoSuchElementException("Category with id [" + id + "] doesn't exist");
        }
    }

    @Override
    public Category get(Long id) {
        return categoryRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Category by id [" + id + "] not founded"));
    }

    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }
}
