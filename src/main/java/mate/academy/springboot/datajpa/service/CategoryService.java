package mate.academy.springboot.datajpa.service;

import mate.academy.springboot.datajpa.model.Category;
import org.springframework.stereotype.Service;

@Service
public interface CategoryService {
    Category getById(Long id);

    Category save(Category category);

    void deleteById(Long id);

    Category update(Category category);
}
