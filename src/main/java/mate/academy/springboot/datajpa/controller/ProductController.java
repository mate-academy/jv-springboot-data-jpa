package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import mate.academy.springboot.datajpa.dto.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.ProductResponseDto;
import mate.academy.springboot.datajpa.dto.mapper.CategoryMapper;
import mate.academy.springboot.datajpa.dto.mapper.ProductMapper;
import mate.academy.springboot.datajpa.service.ProductService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductMapper productMapper;
    private final ProductService productService;
    private final CategoryMapper categoryMapper;

    public ProductController(ProductMapper productMapper,
                             ProductService productService,
                             CategoryMapper categoryMapper) {
        this.productMapper = productMapper;
        this.productService = productService;
        this.categoryMapper = categoryMapper;
    }

    @PostMapping
    public ProductResponseDto create(@RequestBody ProductRequestDto productRequestDto) {
        return productMapper
                .toResponseDto(productService
                .create(productMapper
                .toModel(productRequestDto)));
    }

    @GetMapping("/{id}")
    public ProductResponseDto getById(@PathVariable Long id) {
        return productMapper.toResponseDto(productService.getById(id));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        productService.deleteById(id);
    }

    @GetMapping("/price")
    public List<ProductResponseDto> getByPriceBetween(@RequestParam BigDecimal from,
                                                      BigDecimal to) {
        return productService
               .getByPriceBetween(from, to)
               .stream()
               .map(productMapper::toResponseDto)
               .collect(Collectors.toList());
    }

    @GetMapping
    public List<ProductResponseDto> findAllBy(
            @RequestParam Map<String, String> params) {
        return productService.findAll(params).stream()
                .map(productMapper::toResponseDto)
                .collect(Collectors.toList());
    }
}
