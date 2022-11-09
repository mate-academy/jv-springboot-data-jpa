package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import mate.academy.springboot.datajpa.dto.mapper.ProductMapper;
import mate.academy.springboot.datajpa.dto.requestdto.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.responsedto.ProductResponseDto;
import mate.academy.springboot.datajpa.model.Category;
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

    @GetMapping("/{id}")
    public ProductResponseDto getProductById(@PathVariable Long id) {
        return productMapper.toResponseDto(productService.findProductById(id));
    }

    @PostMapping
    public void addProduct(@RequestBody ProductRequestDto productRequestDto) {
        productService.addProduct(productMapper.toModel(productRequestDto));
    }

    @PutMapping
    public ProductResponseDto updateProduct(@RequestBody ProductRequestDto productRequestDto) {
        return productMapper.toResponseDto(
                productService.updateProduct(
                        productMapper.toModel(productRequestDto)));
    }

    @GetMapping
    public List<ProductResponseDto> getAllProducts() {
        return productService.findAllProducts()
                .stream()
                .map(productMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/price")
    public List<ProductResponseDto> getProductsByPrice(
            @RequestParam BigDecimal from,
            @RequestParam BigDecimal to) {
        return productService.findProductsByPrice(from, to)
                .stream()
                .map(productMapper:: toResponseDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/categories")
    public List<ProductResponseDto> getProductsByCategory(@RequestParam List<Category> categories) {
        return productService.findProductsByCategory(categories)
                .stream()
                .map(productMapper:: toResponseDto)
                .collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable Long id) {
        productService.deleteProductById(id);
    }
}
