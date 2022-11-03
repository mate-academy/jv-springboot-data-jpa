package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import mate.academy.springboot.datajpa.dto.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.ProductResponseDto;
import mate.academy.springboot.datajpa.mapper.CategoryMapper;
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
    private final CategoryMapper categoryMapper;

    public ProductController(ProductService productService,
                             CategoryService categoryService,
                             ProductMapper productMapper,
                             CategoryMapper categoryMapper) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.productMapper = productMapper;
        this.categoryMapper = categoryMapper;
    }

    @PostMapping
    public ProductResponseDto create(ProductRequestDto productRequestDto) {
        Product product = productMapper.toModel(productRequestDto);
        return productMapper.toDto(productService.add(product));
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
    public void update(@RequestBody ProductRequestDto productRequestDto) {
        Product product = productMapper.toModel(productRequestDto);
        Product byId = productService.getById(product.getId());
        byId.setPrice(productRequestDto.getPrice());
        byId.setTitle(productRequestDto.getTitle());
        byId.setCategory(categoryService.getById(productRequestDto.getCategoryId()));
        productService.add(byId);
    }

    @GetMapping("/price")
    public List<ProductResponseDto> getAllProductsByPriceBetween(@RequestParam BigDecimal from,
                                                                 @RequestParam BigDecimal to) {
        return productService.getAllBetween(from, to).stream()
                .map(p -> productMapper.toDto(p))
                .collect(Collectors.toList());
    }

    @GetMapping
    public List<ProductResponseDto> getAllByCategory(@RequestParam("c") List<Long> categoryIds) {
        List<Category> collect = categoryIds.stream()
                .map(i -> categoryService.getById(i))
                .collect(Collectors.toList());
        return productService.getAllByCategory(collect).stream()
                .map(p -> productMapper.toDto(p))
                .collect(Collectors.toList());
    }
}
