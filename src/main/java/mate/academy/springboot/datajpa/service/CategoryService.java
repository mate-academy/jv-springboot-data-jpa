package mate.academy.springboot.datajpa.service;

import mate.academy.springboot.datajpa.model.Category;

public interface CategoryService {
    Category add(Category category);

    Category update(Category category);

    void delete(Category category);

    Category getById(Long id);
}
