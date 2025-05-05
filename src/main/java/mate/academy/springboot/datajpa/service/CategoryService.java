package mate.academy.springboot.datajpa.service;

import mate.academy.springboot.datajpa.model.Category;

public interface CategoryService {
    Category save(Category category);

    Category getById(Long id);

    Category update(Category category);

    void deleteById(Long id);
}
