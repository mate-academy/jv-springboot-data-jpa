package mate.academy.springboot.datajpa.service;

import mate.academy.springboot.datajpa.model.Category;

public interface CategoryService {
    Category findById(Long id);

    Category save(Category product);

    Category update(Category product);

    void deleteById(Long id);
}
