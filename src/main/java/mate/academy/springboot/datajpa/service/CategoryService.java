package mate.academy.springboot.datajpa.service;

import mate.academy.springboot.datajpa.model.Category;

public interface CategoryService {
    public Category save(Category category);

    public Category getById(Long id);

    public Category update(Category category);

    public void deleteById(Long id);
}
