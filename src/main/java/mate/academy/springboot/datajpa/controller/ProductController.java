package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import mate.academy.springboot.datajpa.dto.req.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.resp.ProductResponseDto;
import mate.academy.springboot.datajpa.mapper.ProductMapper;
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
    private final ProductMapper productMapper;

    public ProductController(ProductService productService,
                             CategoryService categoryService,
                             ProductMapper productMapper) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.productMapper = productMapper;
    }

    @PostMapping
    public ProductResponseDto create(@RequestBody ProductRequestDto productDto) {
        Product product = productService.save(productMapper.toModel(productDto));
        return productMapper.toDto(product);
    }

    @GetMapping("/{id}")
    public ProductResponseDto getById(@PathVariable Long id) {
        return productMapper.toDto(productService.getById(id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }

    @PutMapping("/{id}")
    public ProductResponseDto update(@PathVariable Long id,
                                     @RequestBody ProductRequestDto productDto) {
        Product product = productMapper.toModel(productDto);
        product.setId(id);
        return productMapper.toDto(productService.save(product));
    }

    @GetMapping
    public List<ProductResponseDto> getProductsWithPriceBetween(@RequestParam BigDecimal from,
                                                                @RequestParam BigDecimal to) {
        List<Product> productsByPrice = productService.getProductsWithPriceBetween(from, to);
        return productsByPrice.stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/by-category")
    public List<ProductResponseDto> getProductsInCategories(@RequestParam String category) {
        List<Category> categories = Arrays.stream(category.split(","))
                .map(categoryService::findProductByCategory_Name)
                .collect(Collectors.toList());
        List<Product> productsByCategoriesIn = productService.getProductsByCategoriesIn(categories);
        return productsByCategoriesIn.stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }
}
