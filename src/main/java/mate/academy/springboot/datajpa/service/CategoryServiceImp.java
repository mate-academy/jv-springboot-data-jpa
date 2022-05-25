package mate.academy.springboot.datajpa.service;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import mate.academy.springboot.datajpa.mapper.CategoryMapper;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImp extends BaseService<Category> implements CategoryService {

    private final CategoryRepository repository;
    private final CategoryMapper mapper;

    @Override
    @Transactional
    public Category create(Category product) {
        return repository.save(product);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Category> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Category update(Long id, Category source) {
        Category target = repository.findById(id).orElse(null);
        if (target == null) {
            return null;
        }
        Category category = mapper.mapUpdate(source, target);
        return repository.save(category);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Category> findByName(String name) {
        return repository.findCategoryByName(name);
    }
}
