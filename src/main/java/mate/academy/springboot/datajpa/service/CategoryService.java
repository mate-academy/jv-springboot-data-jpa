package mate.academy.springboot.datajpa.service;

import java.util.Optional;
import mate.academy.springboot.datajpa.model.Category;

public interface CategoryService {
    Optional<Category> getById(Long id);

    Category save(Category category);

    void delete(Category category);
}
