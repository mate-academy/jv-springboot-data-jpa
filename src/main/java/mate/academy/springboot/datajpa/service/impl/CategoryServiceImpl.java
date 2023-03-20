package mate.academy.springboot.datajpa.service.impl;

import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.repository.GeneralRepository;
import mate.academy.springboot.datajpa.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends GeneralServiceImpl<Category, Long> implements CategoryService {
    @Autowired
    public CategoryServiceImpl(GeneralRepository<Category, Long> repository) {
        super(repository);
    }
}
