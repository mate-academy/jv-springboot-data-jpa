package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
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
    public ProductResponseDto create(@RequestBody ProductRequestDto requestDto) {
        Product model = productMapper.toModel(requestDto);
        Product product = productService.create(model);
        return productMapper.productResponseDto(product);
    }

    @GetMapping("/{id}")
    public ProductResponseDto getById(@PathVariable Long id) {
        return productMapper.productResponseDto(productService.getById(id));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        productService.deleteById(id);
    }

    @PutMapping("/{id}")
    ProductResponseDto update(@PathVariable Long id,
                              @RequestBody ProductRequestDto productRequestDto) {
        Product product = productMapper.toModel(productRequestDto);
        product.setId(id);
        return productMapper.productResponseDto(productService.update(product));
    }

    @GetMapping("/by-price")
    List<ProductResponseDto> getAllByPriceBetween(@RequestParam BigDecimal from,
                                                  @RequestParam BigDecimal to) {
        List<Product> allByPriceBetween = productService.getAllByPriceBetween(from, to);
        return allByPriceBetween
                .stream()
                .map(productMapper::productResponseDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/by-category")
    List<ProductResponseDto> getAllByCategoriesNames(@RequestParam List<String> categoriesName) {
        List<Product> allByCategories = productService.getAllByCategories(categoriesName);
        return allByCategories
                .stream()
                .map(productMapper::productResponseDto)
                .collect(Collectors.toList());
    }
}
