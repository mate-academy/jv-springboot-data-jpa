package mate.academy.springboot.datajpa.service;

import mate.academy.springboot.datajpa.exception.ServiceDataException;
import mate.academy.springboot.datajpa.model.Category;

public interface CategoryService {
    Category findById(Long id) throws ServiceDataException;

    Category create(Category category);

    void delete(Long id);

    Category update(Category category);
}
