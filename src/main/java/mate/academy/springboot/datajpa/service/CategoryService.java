package mate.academy.springboot.datajpa.service;

import mate.academy.springboot.datajpa.model.Category;

public interface CategoryService {
    Category save(Category category);

    Category findById(Long id);

    Category update(Long id, Category category);

    void delete(Long id);
}
