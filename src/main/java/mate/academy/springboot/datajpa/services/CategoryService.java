package mate.academy.springboot.datajpa.services;

import mate.academy.springboot.datajpa.model.Category;

public interface CategoryService {
    Category create(Category category);

    Category getById(Long id);

    void deleteById(Long id);

}
