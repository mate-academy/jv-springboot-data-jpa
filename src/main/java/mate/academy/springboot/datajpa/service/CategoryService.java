package mate.academy.springboot.datajpa.service;

import mate.academy.springboot.datajpa.model.Category;

public interface CategoryService {
    Category findBy(Long id);

    Category save(Category category);

    void deleteById(Long id);

    void update(String name, Long id);
}
