package mate.academy.springboot.datajpa.service.impl;

import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import mate.academy.springboot.datajpa.dao.CategoryRepository;
import mate.academy.springboot.datajpa.dao.spec.CategorySpecificationManager;
import mate.academy.springboot.datajpa.dao.spec.SpecificationManager;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.service.CategoryService;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    SpecificationManager<Category> manager;
    private final CategoryRepository repository;

    @Override
    public Category save(Category category) {
        return repository.save(category);
    }

    @Override
    public Category getById(Long id) {
        return repository.getById(id);
    }

    @Override
    public Category update(Category category) {
        return repository.save(category);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<Category> findAll(Map<String, String> params) {
        Specification<Category> specification = null;
        for (Map.Entry<String, String> entry : params.entrySet()) {
            Specification<Category> s = manager.get(entry.getKey(),
                    entry.getValue().split(","));
            specification = specification == null
                    ? Specification.where(s) : specification.and(s);
        }
        return repository.findAll(specification);
    }
}
