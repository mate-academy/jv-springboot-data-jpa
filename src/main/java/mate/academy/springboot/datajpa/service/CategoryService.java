package mate.academy.springboot.datajpa.service;

import mate.academy.springboot.datajpa.model.Category;

import java.util.List;

public interface CategoryService {
   Category save(Category category);
   List<Category> getAll();
}
