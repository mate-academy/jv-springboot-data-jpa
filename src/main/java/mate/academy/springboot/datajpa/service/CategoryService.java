package mate.academy.springboot.datajpa.service;

import mate.academy.springboot.datajpa.model.Category;

public interface CategoryService extends GenericService<Category> {
    @Override
    Category add(Category category);

    @Override
    Category update(Category category);
}
