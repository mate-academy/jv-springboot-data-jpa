package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import mate.academy.springboot.datajpa.dto.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.ProductResponseDto;
import mate.academy.springboot.datajpa.dto.mapper.ProductMapper;
import mate.academy.springboot.datajpa.model.Product;
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
    private final ProductService productService;
    private final CategoryService categoryService;
    private final ProductMapper productMapper;

    public ProductController(ProductService productService, CategoryService categoryService,
            ProductMapper productMapper) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.productMapper = productMapper;
    }

    @PostMapping
    public ProductResponseDto create(@RequestBody ProductRequestDto requestDto) {
        Product product = productMapper.toModel(requestDto);
        product.setCategory(categoryService.get(requestDto.getCategoryId()));
        return productMapper.toDto(productService.save(product));
    }

    @GetMapping("/{productId}")
    public ProductResponseDto getById(@PathVariable Long productId) {
        return productMapper.toDto(productService.get(productId));
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id,
            @RequestBody ProductRequestDto requestDto) {
        Product product = productMapper.toModel(requestDto);
        product.setId(id);
        productService.update(product, requestDto.getCategoryId());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.remove(id);
    }

    @GetMapping
    public List<ProductResponseDto> findAll(@RequestParam Map<String, String> params) {
        return productService.findAll(params)
              .stream()
              .map(productMapper::toDto)
              .collect(Collectors.toList());
    }

    @GetMapping("/getByPrice")
    public List<ProductResponseDto> getAllByPriceBetween(@RequestParam BigDecimal from,
            @RequestParam BigDecimal to) {
        return productService.findAllByPriceBetween(from, to)
              .stream()
              .map(productMapper::toDto)
              .collect(Collectors.toList());
    }
}
