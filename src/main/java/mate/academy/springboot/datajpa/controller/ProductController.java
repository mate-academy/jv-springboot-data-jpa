package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import mate.academy.springboot.datajpa.dto.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.ProductResponseDto;
import mate.academy.springboot.datajpa.dto.mapper.RequestDtoMapper;
import mate.academy.springboot.datajpa.dto.mapper.ResponseDtoMapper;
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
    private final RequestDtoMapper<ProductRequestDto, Product> productRequestDtoMapper;
    private final ResponseDtoMapper<ProductResponseDto, Product> productResponseDtoMapper;

    public ProductController(ProductService productService,
                             CategoryService categoryService,
                             RequestDtoMapper<ProductRequestDto, Product>
                                     productRequestDtoMapper,
                             ResponseDtoMapper<ProductResponseDto, Product>
                                     productResponseDtoMapper) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.productRequestDtoMapper = productRequestDtoMapper;
        this.productResponseDtoMapper = productResponseDtoMapper;
    }

    @PostMapping
    public ProductResponseDto create(@RequestBody ProductRequestDto requestDto) {
        Product product = productService.create(productRequestDtoMapper.toModel(requestDto));
        return productResponseDtoMapper.toDto(product);
    }

    @GetMapping
    public List<ProductResponseDto> getAll() {
        return productService.findAll()
                .stream()
                .map(productResponseDtoMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public Product getById(@PathVariable Long id) {
        return productService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }

    @PutMapping("/{id}")
    public ProductResponseDto update(@PathVariable Long id,
                                     @RequestBody ProductRequestDto requestDto) {
        Product product = productService.findById(id);
        product.setTitle(requestDto.getTitle());
        product.setPrice(requestDto.getPrice());
        product.setCategory(categoryService.findById(requestDto.getCategoryId()));
        Product updatedProduct = productService.update(product);
        return productResponseDtoMapper.toDto(updatedProduct);
    }

    @GetMapping("/price-between")
    public List<Product> findAllPriceBetween(@RequestParam BigDecimal from,
                                             @RequestParam BigDecimal to) {
        return productService.findAllByPriceBetween(from, to);
    }

    @GetMapping("/categories")
    public List<Product> findAllByCategoryIn(@RequestParam List<Long> categoryIds) {
        return productService.findAllByCategoryIn(categoryIds);
    }
}

