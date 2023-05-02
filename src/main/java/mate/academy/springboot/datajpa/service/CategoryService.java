package mate.academy.springboot.datajpa.service;

import mate.academy.springboot.datajpa.entity.Category;

public interface CategoryService {
    Category findById(Long categoryId);

    Category save(Category category);

    void delete(Long id);
}
