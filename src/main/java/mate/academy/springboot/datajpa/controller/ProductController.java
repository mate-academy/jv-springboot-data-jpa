package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import mate.academy.springboot.datajpa.dto.*;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.service.CategoryService;
import mate.academy.springboot.datajpa.service.ProductService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final ProductDtoMapper productDtoMapper;
    private final CategoryService categoryService;

    public ProductController(ProductService productService,
                             ProductDtoMapper productDtoMapper,
                             CategoryService categoryService) {
        this.productService = productService;
        this.productDtoMapper = productDtoMapper;
        this.categoryService = categoryService;
    }

    @PostMapping
    public ProductResponseDto save(@RequestBody ProductRequestDto productRequestDto) {
        return productDtoMapper.toDto(productService.save(productDtoMapper.toModel(productRequestDto)));
    }

    @PutMapping(value = "/{id}")
    public ProductResponseDto update(@PathVariable Long id, @RequestBody ProductRequestDto productRequestDto) {
        Product product = productDtoMapper.toModel(productRequestDto);
        product.setId(id);
        return productDtoMapper.toDto(productService.save(product));
    }

    @GetMapping(value = "/{id}")
    public ProductResponseDto getById(@PathVariable Long id) {
        return productDtoMapper.toDto(productService.getById(id));
    }

    @DeleteMapping(value = "/{id}")
    public boolean deleteById(@PathVariable Long id) {
        productService.deleteDyId(id);
        return true;
    }

    @GetMapping(value = "/price")
    public List<ProductResponseDto> findAllByPriceBetween(@RequestParam String from, @RequestParam String to) {
        return productService.findAllByPriceBetween(new BigDecimal(from), new BigDecimal(to))
                .stream()
                .map(productDtoMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/categories")
    public List<ProductResponseDto> getAllInCategories(@RequestParam Map<String, String> params) {
        return productService.getAllInCategories(params).stream()
                .map(productDtoMapper::toDto)
                .collect(Collectors.toList());
    }
}
