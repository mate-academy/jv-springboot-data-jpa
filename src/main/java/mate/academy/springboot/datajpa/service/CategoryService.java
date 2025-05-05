package mate.academy.springboot.datajpa.service;

import mate.academy.springboot.datajpa.model.Category;

public interface CategoryService {
    Category create(Category category);

    Category update(Long id, Category category);

    Category getById(Long id);

    void deleteById(Long id);
}
