package mate.academy.springboot.datajpa.service;

import java.util.Optional;
import mate.academy.springboot.datajpa.model.Category;

public interface CategoryService {
    Category save(Category category);

    Optional<Category> get(Long categoryId);

    void deleteById(Long categoryId);

    void update(Category category);
}
