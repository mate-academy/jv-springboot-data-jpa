package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import mate.academy.springboot.datajpa.dto.response.ProductResponseDto;
import mate.academy.springboot.datajpa.dto.resquest.ProductRequestDto;
import mate.academy.springboot.datajpa.mapper.ProductMapper;
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
    public ProductResponseDto save(@RequestBody ProductRequestDto productRequestDto) {
        Product product = productService.save(productMapper.mapToModel(productRequestDto));
        return productMapper.mapToDto(product);
    }

    @GetMapping()
    public List<ProductResponseDto> findAll() {
        return productService.findAll().stream()
                .map(e -> productMapper.mapToDto(e))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ProductResponseDto getById(@PathVariable Long id) {
        return productMapper.mapToDto(productService.getById(id));
    }

    @GetMapping("/by-price")
    public List<ProductResponseDto> findAllByPriceBetween(@RequestParam BigDecimal from,
                                               @RequestParam BigDecimal to) {
        return productService.findAllByPriceBetween(from, to).stream()
                .map(e -> productMapper.mapToDto(e))
                .collect(Collectors.toList());
    }

    @GetMapping("/by-category")
    public List<ProductResponseDto> findAllByCategoryIn(@RequestParam List<Long> categoryIds) {
        return productService.findAllByCategoryIn(categoryIds).stream()
                .map(e -> productMapper.mapToDto(e))
                .collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    public void delete(Long id) {
        productService.deleteById(id);
    }

    @PutMapping("/{id}")
    public ProductResponseDto update(@PathVariable Long id,
                                     @RequestBody ProductRequestDto requestDto) {
        Product product = productMapper.mapToModel(requestDto);
        product.setId(id);
        return productMapper.mapToDto(productService.save(product));
    }
}
