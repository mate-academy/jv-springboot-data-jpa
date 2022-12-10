package mate.academy.springboot.datajpa.service.impl;

import lombok.AllArgsConstructor;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.repository.CategoryRepository;
import mate.academy.springboot.datajpa.service.CategoryService;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository repository;

    @Override
    public Category save(Category category) {
        return repository.save(category);
    }

    @Override
    public Category getById(Long id) {
        return repository.getById(id);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
