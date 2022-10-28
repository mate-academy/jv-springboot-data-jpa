package mate.academy.springboot.datajpa.service;

import mate.academy.springboot.datajpa.domain.Category;

public interface CategoryService {
    Category save(Category category);

    Category findById(Long id);

    void deleteById(Long id);

    Category findByName(String name);
}
