package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
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

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final ProductMapper productMapper;
    private final CategoryService categoryService;

    public ProductController(ProductService productService, ProductMapper productMapper,
                             CategoryService categoryService) {
        this.productService = productService;
        this.productMapper = productMapper;
        this.categoryService = categoryService;
    }

    @PostMapping
    public ProductResponseDto create(@RequestBody ProductRequestDto requestDto) {
        Product product = productMapper.toModel(requestDto);
        productService.save(product);
        return productMapper.toResponseDto(product);
    }

    @PutMapping("/{id}")
    public ProductResponseDto update(@PathVariable Long id,
                                      @RequestBody ProductRequestDto requestDto) {
        Product product = productMapper.toModel(requestDto);
        product.setId(id);
        productService.update(product);
        return productMapper.toResponseDto(product);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }

    @GetMapping("/{id}")
    public ProductResponseDto getById(@PathVariable Long id) {
        Product product = productService.get(id);
        return productMapper.toResponseDto(product);
    }

    @GetMapping("/price")
    public List<ProductResponseDto> getByPriceBetween(@RequestParam BigDecimal from,
                                                      @RequestParam BigDecimal to) {
        return productService.getProductsByPriceBetween(from, to).stream()
                .map(productMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/category")
    public List<ProductResponseDto> getByCategories(@RequestParam Long[] categoriesIds) {
        List<Category> categories = Arrays.stream(categoriesIds)
                .map(categoryService::get)
                .toList();
        return productService.findProductsByCategoryIsIn(categories).stream()
                .map(productMapper::toResponseDto)
                .collect(Collectors.toList());
    }
}
