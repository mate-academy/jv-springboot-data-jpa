package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import mate.academy.springboot.datajpa.mapper.ProductMapper;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.model.dto.product.ProductRequestDto;
import mate.academy.springboot.datajpa.model.dto.product.ProductResponseDto;
import mate.academy.springboot.datajpa.service.CategoryService;
import mate.academy.springboot.datajpa.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
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
    private ProductService productService;
    private ProductMapper productMapper;
    private CategoryService categoryService;

    @Autowired
    public ProductController(ProductService productService, ProductMapper productMapper,
                             CategoryService categoryService) {
        this.productService = productService;
        this.productMapper = productMapper;
        this.categoryService = categoryService;
    }

    @PostMapping
    public ProductResponseDto create(@RequestBody ProductRequestDto requestDto) {
        Product savedProduct = productService.save(productMapper.toModel(requestDto));
        return productMapper.toResponseDto(savedProduct);
    }

    @GetMapping("/{id}")
    public ProductResponseDto getById(@PathVariable Long id) {
        return productMapper.toResponseDto(productService.getById(id));
    }

    @DeleteMapping("/{id}")
    public ProductResponseDto deleteById(@PathVariable Long id) {
        return productMapper.toResponseDto(productService.deleteById(id));
    }

    @PutMapping("/{id}")
    public ProductResponseDto update(@PathVariable Long id,
                                     @RequestBody ProductRequestDto requestDto) {
        Product product = productMapper.toModel(requestDto);
        product.setId(id);
        return productMapper.toResponseDto(productService.update(product));
    }

    @GetMapping("/price")
    public List<ProductResponseDto> getAllByPriceBetween(@RequestParam BigDecimal from,
                                                         @RequestParam BigDecimal to) {
        return productService.getAllByPriceBetween(from, to).stream()
                .map(productMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/categories")
    public List<ProductResponseDto> getAllByCategoriesIn(@RequestParam List<Long> categoriesIds) {
        return productService.getAllByCategoriesIdIn(categoriesIds).stream()
                .map(productMapper::toResponseDto)
                .collect(Collectors.toList());
    }
}
