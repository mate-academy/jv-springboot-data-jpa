package mate.academy.springboot.datajpa.service;

import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;

public interface CategoryService {
    Category create(Category category);
    Category getById(Long id);
    void deleteById(Long id);
    Category update(Category category);
}
