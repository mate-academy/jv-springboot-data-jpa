package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import mate.academy.springboot.datajpa.dto.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.ProductResponseDto;
import mate.academy.springboot.datajpa.dto.mapper.ProductMapper;
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
    private final ProductService productService;
    private final ProductMapper productMapper;

    public ProductController(ProductService productService, ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @PostMapping
    public ProductResponseDto create(@RequestBody ProductRequestDto productRequestDto) {
        return productMapper.toResponseDto(productService
                .save(productMapper.toModel(productRequestDto)));
    }

    @GetMapping("/{id}")
    public ProductResponseDto get(@PathVariable Long id) {
        return productMapper.toResponseDto(productService.get(id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }

    @PutMapping("/{id}")
    public ProductResponseDto update(@PathVariable Long id,
                                     @RequestBody ProductRequestDto productRequestDto) {
        Product product = productMapper.toModel(productRequestDto);
        product.setId(id);
        return productMapper.toResponseDto(productService.update(product));
    }

    @GetMapping("/by-price-between")
    public List<ProductResponseDto> getAllByPriceBetween(@RequestParam BigDecimal from,
                                                         @RequestParam BigDecimal to) {
        return productService.findAllByPriceBetween(from, to).stream()
                .map(productMapper::toResponseDto)
                .toList();
    }

    @GetMapping("/in-categories")
    public List<ProductResponseDto> getAllByCategoryIdIn(
            @RequestParam Collection<Long> categoryIds) {
        return productService.findAllByCategoryIdIn(categoryIds).stream()
                .map(productMapper::toResponseDto)
                .toList();
    }
}
