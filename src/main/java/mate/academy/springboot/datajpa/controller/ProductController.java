package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import mate.academy.springboot.datajpa.dto.request.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.response.ProductResponseDto;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.service.CategoryService;
import mate.academy.springboot.datajpa.service.ProductService;
import mate.academy.springboot.datajpa.service.mapper.ProductMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
        Product product = productMapper.toModel(requestDto);
        product = productService.save(product);
        return productMapper.toDto(product);
    }

    @GetMapping("/{productId}")
    public ProductResponseDto getById(@PathVariable Long productId) {
        Product product = productService.getById(productId);
        return productMapper.toDto(product);
    }

    @DeleteMapping("/{productId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void delete(@PathVariable Long productId) {
        productService.delete(productId);
    }

    @PutMapping("/{productId}")
    public ProductResponseDto update(@PathVariable Long productId,
                                     @RequestBody ProductRequestDto requestDto) {
        Product product = productMapper.toModel(requestDto);
        product.setId(productId);
        return productMapper.toDto(productService.update(product));
    }

    @GetMapping(params = {"from", "to"})
    public List<ProductResponseDto> getAllPriceBetween(@RequestParam BigDecimal from,
                                                       @RequestParam BigDecimal to) {
        List<Product> products = productService.findAllByPriceBetween(from, to);
        return products.stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping(params = {"category"})
    public List<ProductResponseDto> getAllInCategories(@RequestParam String category) {
        List<Category> categories = Arrays.stream(category.split(","))
                .map(Long::parseLong)
                .map(categoryService::getById)
                .collect(Collectors.toList());
        List<Product> products = productService.findAllByCategoryIn(categories);
        return products.stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }
}
