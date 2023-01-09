package mate.academy.springboot.datajpa.service;

import java.util.List;
import mate.academy.springboot.datajpa.model.Category;

public interface CategoryService extends AbstractService<Category, Long> {

    List<Category> findAll();
}
