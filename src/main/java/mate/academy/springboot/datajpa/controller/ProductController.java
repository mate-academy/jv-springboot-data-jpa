package mate.academy.springboot.datajpa.controller;

import mate.academy.springboot.datajpa.mapper.impl.ProductRequestDtoMapper;
import mate.academy.springboot.datajpa.mapper.impl.ProductResponseDtoMapper;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.model.dto.request.ProductRequestDto;
import mate.academy.springboot.datajpa.model.dto.response.ProductResponseDto;
import mate.academy.springboot.datajpa.service.CategoryService;
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
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final CategoryService categoryService;
    private final ProductService productService;
    private final ProductResponseDtoMapper productResponseDtoMapper;
    private final ProductRequestDtoMapper productRequestDtoMapper;

    @Autowired
    public ProductController(CategoryService categoryService, ProductService productService,
                             ProductResponseDtoMapper productResponseDtoMapper,
                             ProductRequestDtoMapper productRequestDtoMapper) {
        this.categoryService = categoryService;
        this.productService = productService;
        this.productResponseDtoMapper = productResponseDtoMapper;
        this.productRequestDtoMapper = productRequestDtoMapper;
    }

    @PostMapping
    public ProductResponseDto create(@RequestBody ProductRequestDto productRequestDto) {
        Product product = productRequestDtoMapper.toModel(productRequestDto);
        Category category = categoryService.getByName(product.getCategory().getName());
        category = category != null ? category : product.getCategory();
        product.setCategory(categoryService.save(category));
        return productResponseDtoMapper.toDto(productService.save(product));
    }

    @GetMapping("/{product_id}")
    public ProductResponseDto getById(@PathVariable(name = "product_id") Long productId) {
        return productResponseDtoMapper.toDto(productService.getById(productId));
    }

    @DeleteMapping
    public void deleteById(@RequestParam(name = "product_id") Long productId) {
        productService.delete(productService.getById(productId));
    }

    @PutMapping
    public ProductResponseDto update(@RequestParam(name = "product_id") Long productId,
                                     @RequestBody ProductRequestDto productRequestDto) {
        Product product = productRequestDtoMapper.toModel(productRequestDto);
        product.setId(productId);
        Category category = categoryService.getByName(product.getCategory().getName());
        category = category != null ? category : product.getCategory();
        category.setName(product.getCategory().getName());
        product.setCategory(categoryService.update(category));
        return productResponseDtoMapper.toDto(productService.update(product));
    }

    @GetMapping("/price")
    public List<ProductResponseDto> findAll(@RequestParam BigDecimal from, @RequestParam BigDecimal to) {
        return productService.findAll(from, to).stream()
                .map(productResponseDtoMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/filter")
    public List<ProductResponseDto> findAll(@RequestParam Map<String, String> params) {
        return productService.findAll(params).stream()
                .map(productResponseDtoMapper::toDto)
                .collect(Collectors.toList());
    }
}
