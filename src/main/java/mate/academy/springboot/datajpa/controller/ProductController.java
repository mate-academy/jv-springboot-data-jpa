package mate.academy.springboot.datajpa.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import mate.academy.springboot.datajpa.mapper.ProductMapper;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.model.dto.ProductRequestDto;
import mate.academy.springboot.datajpa.model.dto.ProductResponseDto;
import mate.academy.springboot.datajpa.service.CategoryService;
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
    private final ProductMapper productMapper;
    private final ProductService productService;
    private final CategoryService categoryService;

    public ProductController(ProductMapper productMapper,
                             ProductService productService,
                             CategoryService categoryService) {
        this.productMapper = productMapper;
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @PostMapping
    public ProductResponseDto save(@RequestBody ProductRequestDto requestDto) {
        return productMapper
                .toResponseDto(
                        productService
                                .save(
                                        productMapper
                                                .toModel(requestDto)));
    }

    @GetMapping("/{id}")
    public ProductResponseDto getById(@PathVariable Long id) {
        return productMapper.toResponseDto(productService.getById(id));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        productService.deleteById(id);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id,@RequestBody ProductRequestDto dto) {
        Product product = productMapper.toModel(dto);
        product.setId(id);
        productService.save(product);
    }

    @GetMapping
    public List<ProductResponseDto> findAll(@RequestParam Map<String, String> params) {
        return productService.findAll(params).stream()
                .map(productMapper::toResponseDto)
                .collect(Collectors.toList());
    }
}
