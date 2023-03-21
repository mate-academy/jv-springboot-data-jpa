package mate.academy.springboot.service;

import mate.academy.springboot.model.Category;

public interface CategoryService {
    Category save(Category category);

    Category get(Long id);

    void deleteById(Long id);
}
