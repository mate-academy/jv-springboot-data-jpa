package mate.academy.springboot.datajpa.service;

import mate.academy.springboot.datajpa.model.Category;

public interface CategoryService {
    Category save(Category category);

    Category update(Category category);

    void delete(Long id);

    Category findById(Long id);
}
