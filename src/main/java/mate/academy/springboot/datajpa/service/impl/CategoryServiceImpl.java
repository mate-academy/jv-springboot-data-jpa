package mate.academy.springboot.datajpa.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import lombok.AllArgsConstructor;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.repository.CategoryRepository;
import mate.academy.springboot.datajpa.service.CategoryService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;

    @Override
    public Category save(Category entity) {
        return categoryRepository.save(entity);
    }

    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("No such category with id: "
                + id + " exists"));
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public void update(Category entity) {
        if (!categoryRepository.existsById(entity.getId())) {
            throw new NoSuchElementException("can't update non "
                    + "existent category by id: " + entity.getId());
        }
        categoryRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }
}
