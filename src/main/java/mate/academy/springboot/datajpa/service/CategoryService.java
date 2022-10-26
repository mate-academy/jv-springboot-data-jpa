package mate.academy.springboot.datajpa.service;

import mate.academy.springboot.datajpa.domain.Category;

public interface CategoryService {
    Category create(Category category);

    Category findById(Long id);

    void deleteById(Long id);

    Category update(Long id, Category category);

    Category findByName(String name);
}
