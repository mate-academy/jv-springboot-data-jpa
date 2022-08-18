package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import mate.academy.springboot.datajpa.dto.request.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.response.ProductResponseDto;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.service.ProductService;
import mate.academy.springboot.datajpa.service.mapper.ProductMapper;
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
    public ProductResponseDto createProduct(@RequestBody ProductRequestDto requestDto) {
        Product product = productService.save(productMapper.mapToModel(requestDto));
        return productMapper.mapToDto(product);
    }

    @GetMapping("/{id}")
    public ProductResponseDto getProduct(@PathVariable Long id) {
        return productMapper.mapToDto(productService.findById(id));
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.delete(id);
    }

    @PutMapping("/{id}")
    public void updateProduct(@PathVariable Long id, @RequestBody ProductRequestDto requestDto) {
        productService.update(id, productMapper.mapToModel(requestDto));
    }

    @GetMapping("/in-price-between")
    public List<ProductResponseDto> getProductsInPrice(@RequestParam BigDecimal from,
                                                       @RequestParam BigDecimal to) {
        List<Product> products = productService.getProductsBetween(from, to);
        return products.stream()
                .map(productMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @GetMapping
    public List<ProductResponseDto> getProductsInCategories(
            @RequestParam Map<String, String> params) {
        return productService.findAllInCategories(params).stream()
                .map(productMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
