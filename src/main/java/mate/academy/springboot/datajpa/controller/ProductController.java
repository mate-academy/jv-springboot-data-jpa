package mate.academy.springboot.datajpa.controller;

import mate.academy.springboot.datajpa.dto.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.ProductResponseDto;
import mate.academy.springboot.datajpa.dto.mapper.ProductMapper;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.service.CategoryService;
import mate.academy.springboot.datajpa.service.ProductService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;
    private final ProductMapper productMapper;

    public ProductController(ProductService productService, CategoryService categoryService,
                             ProductMapper productMapper) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.productMapper = productMapper;
    }

    @PostMapping
    public ProductResponseDto create(@RequestBody ProductRequestDto requestDto) {
        return productMapper.mapToDto(productService.create(
                productMapper.mapToModel(requestDto)));
    }

    @GetMapping("/{id}")
    ProductResponseDto getById(@PathVariable Long id) {
        return productMapper.mapToDto(productService.getById(id));
    }

    @DeleteMapping("/{id}")
    void deleteById(@PathVariable Long id) {
        productService.deleteById(id);
    }

    @PutMapping("/{id}")
    ProductResponseDto update(@RequestBody ProductRequestDto requestDto, @PathVariable Long id) {
        Product product = productService.getById(id);
        product.setPrice(requestDto.getPrice());
        product.setTitle(requestDto.getTitle());
        product.setCategory(categoryService.getById(requestDto.getCategoryId()));
        return productMapper.mapToDto(productService.update(product));
    }

    @GetMapping("/price-between")
    public List<ProductResponseDto> getAllByPriceBetween(@RequestParam BigDecimal from,
                                                         @RequestParam BigDecimal to) {
        return productService.getAllByPriceBetween(from, to).stream()
                .map(productMapper::mapToDto)
                .toList();
    }

    @GetMapping("/categories")
    public List<ProductResponseDto> getAllByCategories(@RequestParam List<Long> ids) {
        List<Category> categories = ids.stream()
                .map(categoryService::getById)
                .toList();
        return productService.getAllByCategories(categories).stream()
                .map(productMapper::mapToDto)
                .toList();
    }
}
