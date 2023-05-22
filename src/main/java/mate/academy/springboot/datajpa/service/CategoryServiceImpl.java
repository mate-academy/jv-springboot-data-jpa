package mate.academy.springboot.datajpa.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.repository.CategoryRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category getById(Long id) {
        return categoryRepository.getReferenceById(id);
    }

    @Override
    public void delete(Long id) {
        categoryRepository.delete(getById(id));
    }

    @Override
    public Category update(Category category) {
        Category reference = categoryRepository.getReferenceById(category.getId());
        reference.setName(category.getName());
        return categoryRepository.save(reference);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
}
