package mate.academy.springboot.datajpa.service;

import mate.academy.springboot.datajpa.model.Category;
import java.util.Optional;

public interface CategoryService {
    Category create(Category category);
    Optional<Category> getById(Long id);
    void deleteById(Long id);
    Category update(Category category);
}
