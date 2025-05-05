package mate.academy.springboot.datajpa.service;

import mate.academy.springboot.datajpa.model.entity.Category;

public interface CategoryService {
    Category save(Category category);

    Category findById(Long id);

    Category findByNameOrSave(String name);

    void delete(Long id);

    Category update(Category category);
}
