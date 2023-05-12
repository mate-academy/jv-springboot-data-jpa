package mate.academy.springboot.datajpa.services;

import mate.academy.springboot.datajpa.models.Category;

public interface CategoryService {
    Category save(Category category);

    Category findById(Long id);

    void delete(Long id);

    Category update(Category category);
}
