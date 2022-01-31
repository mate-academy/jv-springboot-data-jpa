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
    public ProductResponseDto create(@RequestBody ProductRequestDto requestDto) {
        Product product = productService.save(productMapper.toModel(requestDto));
        return productMapper.toDto(product);
    }

    @GetMapping("/{id}")
    public ProductResponseDto getById(@PathVariable Long id) {
        Product product = productService.getById(id);
        return productMapper.toDto(product);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        productService.deleteById(id);
    }

    @PutMapping("/{id}")
    public ProductResponseDto update(@PathVariable Long id,
                                     @RequestBody ProductRequestDto requestDto) {
        Product product = productMapper.toModel(requestDto);
        product.setId(id);
        productService.update(product);
        return productMapper.toDto(product);
    }

    @GetMapping("/price")
    public List<ProductResponseDto> getProductsByPriceBetween(@RequestParam Long from,
                                                              @RequestParam Long to) {
        return productService
                .getProductsByPriceBetween(BigDecimal.valueOf(from), BigDecimal.valueOf(to))
                .stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/available")
    public List<ProductResponseDto> findAll(@RequestParam Map<String, String> params) {
        return productService.findAll(params).stream()
                .map(p -> productMapper.toDto(p))
                .collect(Collectors.toList());
    }
}
