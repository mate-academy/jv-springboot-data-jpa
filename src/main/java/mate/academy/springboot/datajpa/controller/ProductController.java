package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import mate.academy.springboot.datajpa.dto.mapper.ProductMapper;
import mate.academy.springboot.datajpa.dto.product.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.product.ProductResponseDto;
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
    public ProductResponseDto create(@RequestBody ProductRequestDto requestDto) {
        return productMapper.toResponseDto(productMapper.toModel(requestDto));
    }

    @GetMapping("/{id}")
    public ProductResponseDto getById(@PathVariable Long id) {
        return productMapper.toResponseDto(productService.get(id));
    }

    @DeleteMapping("/{id}")
    void deleteById(@PathVariable Long id) {
        productService.delete(id);
    }

    @PutMapping("/{id}")
    public ProductResponseDto update(@RequestBody ProductRequestDto requestDto,
                                     @PathVariable Long id) {
        Product product = productMapper.toModel(requestDto);
        product.setId(id);
        return productMapper.toResponseDto(productService.save(product));
    }

    @GetMapping("/{from},{to}")
    public List<ProductResponseDto> findAllInRange(@PathVariable Long from,
                                                   @PathVariable Long to) {
        return productService
                .findAllByPriceBetween(BigDecimal.valueOf(from), BigDecimal.valueOf(to))
                .stream()
                .map(productMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @GetMapping
    public List<ProductResponseDto> findAllInCategories(
            @RequestParam Map<String, String> params) {
        return productService.findAll(params)
                .stream()
                .map(productMapper::toResponseDto)
                .collect(Collectors.toList());
    }
}
