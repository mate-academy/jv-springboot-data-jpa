package mate.academy.springboot.datajpa.service;

import mate.academy.springboot.datajpa.model.Category;

public interface CategoryService {
    Category findById(Long id);

    Category save(Category category);

    void deleteById(Long id);
}