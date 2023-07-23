package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import java.util.List;
import mate.academy.springboot.datajpa.dto.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.ProductResponseDto;
import mate.academy.springboot.datajpa.dto.mapper.DtoMapper;
import mate.academy.springboot.datajpa.model.Product;
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
    private final DtoMapper<Product, ProductRequestDto, ProductResponseDto> productMapper;
    private final ProductService productService;

    public ProductController(ProductService productService,
            DtoMapper<Product, ProductRequestDto, ProductResponseDto> productMapper) {
        this.productMapper = productMapper;
        this.productService = productService;
    }

    @PostMapping
    public ProductResponseDto create(@RequestBody ProductRequestDto requestDto) {
        return productMapper.toDto(productService.save(productMapper.toModel(requestDto)));
    }

    @GetMapping("/{id}")
    public ProductResponseDto get(@PathVariable Long id) {
        return productMapper.toDto(productService.getById(id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }

    @PutMapping("/{id}")
    public ProductResponseDto update(@PathVariable Long id,
            @RequestBody ProductRequestDto requestDto) {
        Product product = productMapper.toModel(requestDto);
        product.setId(id);
        productService.update(product);
        return productMapper.toDto(product);
    }

    @GetMapping("/by-price-range")
    public List<ProductResponseDto> getAll(@RequestParam BigDecimal from,
            @RequestParam BigDecimal to) {
        return productService.getAllWherePriceBetween(from, to)
                .stream()
                .map(p -> productMapper.toDto(p))
                .toList();
    }

    @GetMapping("/by-categories")
    public List<ProductResponseDto> getAll(@RequestParam List<Long> categories) {
        List<Product> products = categories.stream()
                .map(id -> productService.getAllByCategory(id))
                .flatMap(p -> p.stream()).toList();
        return products.stream()
                .map(p -> productMapper.toDto(p))
                .toList();
    }
}
