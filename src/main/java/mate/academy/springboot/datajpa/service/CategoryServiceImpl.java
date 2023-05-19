package mate.academy.springboot.datajpa.service;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.repository.CategoryRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {
    @NotNull
    private final CategoryRepository categoryRepository;

    @Override
    public Category create(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category get(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cannot find category by id " + id));
    }

    @Override
    public void delete(Category category) {
        categoryRepository.delete(category);
    }

    @Override
    public Category update(Category category) {
        long id = category.getId();
        if (categoryRepository.findById(id).isEmpty()) {
            throw new RuntimeException("There is no category found for id " + id);
        }
        categoryRepository.save(category);
        return category;
    }
}
