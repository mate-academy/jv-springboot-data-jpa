package mate.academy.springboot.datajpa.controller;

import mate.academy.springboot.datajpa.dto.RequestCategory;
import mate.academy.springboot.datajpa.dto.ResponseCategory;
import mate.academy.springboot.datajpa.dto.mapper.CategoryMapper;
import mate.academy.springboot.datajpa.service.CategoryService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService service;
    private final CategoryMapper mapper;

    public CategoryController(CategoryService service,
                              CategoryMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseCategory create(@RequestBody RequestCategory req) {
        return mapper.toResponseCategory(service.save(mapper.toModel(req)));
    }

    @GetMapping("/{id}")
    public ResponseCategory get(@PathVariable Long id) {
        return mapper.toResponseCategory(service.get(id));
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        String deletedCategory = service.get(id).getName();
        service.delete(id);
        return "Category " + deletedCategory + " was deleted";
    }

    @PutMapping("/{id}")
    public String update(@RequestBody RequestCategory req, @PathVariable Long id) {
        String oldName = service.get(id).getName();
        service.update(mapper.toModel(req), id);
        String newName = service.get(id).getName();
        return "The category changed its name from " + oldName + " to " + newName;
    }
}
