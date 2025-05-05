package mate.academy.springboot.service;

import mate.academy.springboot.model.Category;

public interface CategoryService {
    Category add(Category category);

    Category get(Long id);

    void delete(Long id);

    Category update(Category category);
}
