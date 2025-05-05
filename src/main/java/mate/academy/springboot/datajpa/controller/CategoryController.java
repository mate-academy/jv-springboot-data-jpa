package mate.academy.springboot.datajpa.controller;

import mate.academy.springboot.datajpa.model.Category;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @PostMapping("/add")
    public Category save(@RequestBody Category category) {
        return new Category();
    }

    @GetMapping("/{id}")
    public Category get(@PathVariable Long id) {
        return new Category();
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        return "category with id " + id + "was deleted";
    }

    @PutMapping("/{id}")
    public Category update(@PathVariable Long id,
                           @RequestBody Category category) {
        return new Category();
    }
}
