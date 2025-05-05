package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import mate.academy.springboot.datajpa.dto.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.ProductResponseDto;
import mate.academy.springboot.datajpa.dto.mapper.ProductMapper;
import mate.academy.springboot.datajpa.model.Product;
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
    private final ProductService productService;
    private final ProductMapper productMapper;

    @Autowired
    public ProductController(ProductService productService,
                             ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @PostMapping
    public ProductResponseDto create(@RequestBody ProductRequestDto productRequestDto) {
        Product product = productService.create(productMapper.toModel(productRequestDto));
        return productMapper.toResponseDto(product);
    }

    @GetMapping("/{id}")
    public ProductResponseDto getById(@PathVariable("id") Long id) {
        return productMapper.toResponseDto(productService.getById(id));
    }

    @DeleteMapping("/{id}")
    public ProductResponseDto deleteById(@PathVariable("id") Long id) {
        return productMapper.toResponseDto(productService.deleteById(id));
    }

    @PutMapping("/{id}")
    public ProductResponseDto update(@PathVariable("id") Long id,
                          @RequestBody ProductRequestDto productRequestDto) {
        return productMapper.toResponseDto(productService.update(id,
                productMapper.toModel(productRequestDto)));
    }

    @GetMapping("/between-price")
    public List<ProductResponseDto> getAllProductsByPrice(
            @RequestParam(value = "min") BigDecimal minPrice,
            @RequestParam(value = "max") BigDecimal maxPrice) {
        return productService.getAllByPriceBetween(minPrice, maxPrice)
                .stream()
                .map(productMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/category")
    public List<ProductResponseDto> getAllByCategory(@RequestParam(value = "category")
                                                     Map<String, String> params) {
        return productService.getAllProductsWithCategories(params)
                .stream()
                .map(productMapper::toResponseDto)
                .collect(Collectors.toList());
    }
}
