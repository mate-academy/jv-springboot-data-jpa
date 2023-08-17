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
    public Category add(Category model) {
        return categoryRepository.save(model);
    }

    @Override
    public Category get(Long id) {
        return categoryRepository.getReferenceById(id);
    }

    @Override
    public Category update(Category model) {
        return categoryRepository.save(model);
    }

    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }
}
