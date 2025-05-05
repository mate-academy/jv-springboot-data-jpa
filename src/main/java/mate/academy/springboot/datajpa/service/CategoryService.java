package mate.academy.springboot.datajpa.service;

import mate.academy.springboot.datajpa.model.Category;
import org.springframework.stereotype.Service;

@Service
public interface CategoryService {
    Category saveCategory(Category category);

    Category getCategoryById(Long id);

    void deleteCategoryById(Long id);

    Category updateCategory(Category category);
}
