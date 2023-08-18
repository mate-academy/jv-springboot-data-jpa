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
    private final CategoryRepository repository;

    @Override
    public Category add(Category category) {
        return repository.save(category);
    }

    @Override
    public Category getById(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Can`t find category with id: " + id));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Category update(Category category, Long id) {
        Category categoryFromDb = this.getById(id);
        categoryFromDb.setName(category.getName());
        return repository.save(categoryFromDb);
    }
}
