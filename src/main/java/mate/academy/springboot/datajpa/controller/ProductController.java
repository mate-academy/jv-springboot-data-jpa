package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import mate.academy.springboot.datajpa.dto.ProductDto;
import mate.academy.springboot.datajpa.dto.ProductDtoMapper;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.service.CategoryService;
import mate.academy.springboot.datajpa.service.ProductService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final ProductDtoMapper productDtoMapper;
    private final CategoryService categoryService;

    public ProductController(ProductService productService, ProductDtoMapper productDtoMapper, CategoryService categoryService) {
        this.productService = productService;
        this.productDtoMapper = productDtoMapper;
        this.categoryService = categoryService;
    }

    @PostMapping
    public ProductDto save(@RequestBody ProductDto productDto) {
        return productDtoMapper.toDto(productService.save(productDtoMapper.toModel(productDto)));
    }

    @GetMapping(value = "/{id}")
    public ProductDto getById(@PathVariable Long id) {
        return productDtoMapper.toDto(productService.getById(id));
    }

    @DeleteMapping(value = "/{id}")
    public String deleteById(@PathVariable Long id) {
        productService.deleteDyId(id);
        return "Done!";
    }

    @GetMapping(value = "/by_prices")
    public List<ProductDto> findAllByPriceBetween(@RequestParam String from, @RequestParam String to) {
        return productService.findAllByPriceBetween(new BigDecimal(from), new BigDecimal(to))
                .stream()
                .map(productDtoMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/by_categories")
    public List<ProductDto> getAllInCategoryIn(@RequestParam List<String> categoryId) {
        List<Category> categories = categoryId.stream()
                .map(s -> categoryService.getById(Long.parseLong(s)))
                .collect(Collectors.toList());
        return productService.getAllInCategoryIn(categories).stream()
                .map(productDtoMapper::toDto)
                .collect(Collectors.toList());
    }
}
