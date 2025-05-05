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
    public Category getById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Can`t find category by id: " + id));
    }

    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }
}
