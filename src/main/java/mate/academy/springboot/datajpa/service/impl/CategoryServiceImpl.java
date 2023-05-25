package mate.academy.springboot.datajpa.service.impl;

import lombok.RequiredArgsConstructor;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.repository.CategoryRepository;
import mate.academy.springboot.datajpa.service.CategoryService;
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
    public Category get(Long id) {
        return categoryRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Can't get Category from DB by ID: " + id));
    }

    @Override
    public boolean delete(Long id) {
        return categoryRepository.deleteProductByIdIs(id);
    }

    @Override
    public Category update(Category category) {
        try {
            get(category.getId());
            return categoryRepository.save(category);
        } catch (RuntimeException e) {
            throw new RuntimeException("Can't update Category in DB: " + category);
        }
    }
}
