package mate.academy.springboot.datajpa.service;

import mate.academy.springboot.datajpa.model.Category;
import org.springframework.stereotype.Service;

@Service
public interface CategoryService {
    public Category save(Category category);

    public Category get(Long id);

    public void delete(Long id);

    public Category update(Category category);
}
