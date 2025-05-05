package mate.academy.springboot.datajpa.service.impl;

import java.util.NoSuchElementException;
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
    public Category save(Category model) {
        return categoryRepository.save(model);
    }

    @Override
    public Category get(Long id) {
        return categoryRepository.findById(id).orElseThrow(()
                -> new NoSuchElementException("Can`t find category by id: " + id));
    }

    @Override
    public void remove(Long id) {
        categoryRepository.deleteById(id);
    }
}
