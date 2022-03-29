package mate.academy.springboot.datajpa.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.repository.CategoryRepository;
import mate.academy.springboot.datajpa.service.CategoryService;
import mate.academy.springboot.datajpa.service.ProductService;
import mate.academy.springboot.datajpa.util.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;
    private ProductService productService;

    @Autowired
    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public Category save(Category element) {
        return categoryRepository.getCategoryByName(element.getName())
                .orElseGet(() -> categoryRepository.getCategoryByName(element.getName())
                .orElse(categoryRepository.save(element)));
    }

    @Override
    public Category getById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new SystemException("No category found by id " + id));
    }

    @Override
    public void deleteById(Long id) {
        try {
            productService.getAll().stream()
                    .filter(p -> Objects.equals(p.getCategory().getId(), id))
                    .forEach(p -> p.setCategory(null));
            categoryRepository.deleteById(id);
        } catch (RuntimeException e) {
            throw new SystemException("No category found by id " + id);
        }
    }

    @Override
    public Category update(Category element) {
        return categoryRepository.save(element);
    }

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getByName(String name) {
        return categoryRepository.getCategoryByName(name).orElseThrow(NoSuchElementException::new);
    }
}
