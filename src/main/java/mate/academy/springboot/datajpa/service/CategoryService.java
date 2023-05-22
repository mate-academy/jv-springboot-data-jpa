package mate.academy.springboot.datajpa.service;

import mate.academy.springboot.datajpa.model.Category;

public interface CategoryService {
    Category create(Category category);

    Category getById(Long id);

    Category update(Category category);

    void deleteById(Long id);
}
