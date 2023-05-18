package mate.academy.springboot.datajpa.controller;

import java.util.List;
import mate.academy.springboot.datajpa.dto.CategoryRequestDto;
import mate.academy.springboot.datajpa.dto.CategoryResponseDto;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.service.CategoryService;
import mate.academy.springboot.datajpa.service.ProductService;
import mate.academy.springboot.datajpa.service.mapper.RequestDtoMapper;
import mate.academy.springboot.datajpa.service.mapper.ResponseDtoMapper;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final ProductService productService;
    private final CategoryService categoryService;
    private final RequestDtoMapper<CategoryRequestDto, Category> categoryRequestDtoMapper;
    private final ResponseDtoMapper<CategoryResponseDto, Category> categoryResponseDtoMapper;

    public CategoryController(ProductService productService,
                              CategoryService categoryService,
                              RequestDtoMapper<CategoryRequestDto,
                                      Category> categoryRequestDtoMapper,
                              ResponseDtoMapper<CategoryResponseDto,
                                      Category> categoryResponseDtoMapper) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.categoryRequestDtoMapper = categoryRequestDtoMapper;
        this.categoryResponseDtoMapper = categoryResponseDtoMapper;
    }

    @GetMapping("/inject")
    public String injectProducts() {
        Category food = new Category();
        food.setName("food");
        categoryService.create(food);

        Category silverware = new Category();
        silverware.setName("silverware");
        categoryService.create(silverware);

        return "Success!";
    }

    @GetMapping
    public CategoryResponseDto get(@RequestParam Long id) {
        return categoryResponseDtoMapper.mapToDto(categoryService.get(id));
    }

    @PostMapping
    public CategoryResponseDto create(@RequestBody CategoryRequestDto categoryRequestDto) {
        return categoryResponseDtoMapper.mapToDto(
                categoryService.create(
                        categoryRequestDtoMapper.mapToModel(categoryRequestDto)));
    }

    @PutMapping
    public CategoryResponseDto update(@RequestBody CategoryRequestDto categoryRequestDto) {
        return categoryResponseDtoMapper.mapToDto(
                categoryService.update(
                        categoryRequestDtoMapper.mapToModel(categoryRequestDto)));
    }

    @DeleteMapping
    public String delete(@RequestParam Long id) {
        List<Product> products = productService.getByCategory(categoryService.get(id));
        for (Product product : products) {
            productService.delete(product);
        }
        categoryService.delete(categoryService.get(id));
        return "Category with id %s has been deleted".formatted(id);
    }
}
