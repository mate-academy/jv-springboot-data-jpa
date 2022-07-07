package mate.academy.springboot.datajpa.service;

import mate.academy.springboot.datajpa.exception.CategoryNotFoundException;
import mate.academy.springboot.datajpa.model.Category;

public interface CategoryService {
    Category save(Category category);

    Category findById(Long id) throws CategoryNotFoundException;

    void deleteById(Long id);

    Category update(Category category);
}
