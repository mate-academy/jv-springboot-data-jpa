package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import java.util.List;
import mate.academy.springboot.datajpa.dto.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.ProductResponseDto;
import mate.academy.springboot.datajpa.dto.mapper.DtoMapper;
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
    private final CategoryService categoryService;
    private final DtoMapper<Product, ProductResponseDto, ProductRequestDto> productMapper;

    public ProductController(ProductService productService, CategoryService categoryService,
            DtoMapper<Product, ProductResponseDto, ProductRequestDto> productMapper) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.productMapper = productMapper;
    }

    @PostMapping
    public ProductResponseDto create(@RequestBody ProductRequestDto productRequestDto) {
        return productMapper.toDto(
                productService.create(
                        productMapper.toModel(productRequestDto)));
    }

    @GetMapping("/{id}")
    public ProductResponseDto getProductById(@PathVariable Long id) {
        return productMapper.toDto(productService.getById(id));
    }

    @PutMapping("/{id}")
    public ProductResponseDto update(@PathVariable Long id,
                                     @RequestBody ProductRequestDto productRequestDto) {
        Product product = productMapper.toModel(productRequestDto);
        product.setId(id);
        return productMapper.toDto(
                productService.update(product));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.deleteById(id);
    }

    @GetMapping("/price")
    public List<ProductResponseDto> getProductsByPrice(@RequestParam("min") BigDecimal min,
                                                      @RequestParam("max") BigDecimal max) {
        return productService.getProductsByPriceBetween(min, max)
                .stream()
                .map(productMapper::toDto)
                .toList();
    }

    @GetMapping("/categories")
    public List<ProductResponseDto> getProductsByCategory(
            @RequestParam("categoryId") List<Long> categoryIds) {
        List<Category> categories = categoryIds.stream()
                .map(Category::new)
                .toList();
        return productService.getProductsByCategories(categories)
                .stream()
                .map(productMapper::toDto)
                .toList();
    }
}
