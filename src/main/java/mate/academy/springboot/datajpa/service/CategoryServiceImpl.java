package mate.academy.springboot.datajpa.service;

import lombok.AllArgsConstructor;
import mate.academy.springboot.datajpa.dao.CategoryRepository;
import mate.academy.springboot.datajpa.model.Category;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
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
    public Category update(Category category) {
        return repository.save(category);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
