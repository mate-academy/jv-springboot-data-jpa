package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import mate.academy.springboot.datajpa.mapper.RequestDtoMapper;
import mate.academy.springboot.datajpa.mapper.ResponseDtoMapper;
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

@RestController
@RequestMapping("/products")
public class ProductController {
    private final CategoryService categoryService;
    private final ProductService productService;
    private final ResponseDtoMapper<ProductResponseDto, Product> productResponseDtoMapper;
    private final RequestDtoMapper<ProductRequestDto, Product> productRequestDtoMapper;

    @Autowired
    public ProductController(
            CategoryService categoryService,
            ProductService productService,
            ResponseDtoMapper<ProductResponseDto, Product> productResponseDtoMapper,
            RequestDtoMapper<ProductRequestDto, Product> productRequestDtoMapper) {
        this.categoryService = categoryService;
        this.productService = productService;
        this.productResponseDtoMapper = productResponseDtoMapper;
        this.productRequestDtoMapper = productRequestDtoMapper;
    }

    @PostMapping
    public ProductResponseDto create(@RequestBody ProductRequestDto productRequestDto) {
        Product product = productRequestDtoMapper.toModel(productRequestDto);
        product.setCategory(categoryService.save(product.getCategory()));
        return productResponseDtoMapper.toDto(productService.save(product));
    }

    @GetMapping("/{id}")
    public ProductResponseDto getById(@PathVariable(name = "id") Long productId) {
        return productResponseDtoMapper.toDto(productService.getById(productId));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable(name = "id") Long productId) {
        productService.delete(productService.getById(productId));
    }

    @PutMapping("/{id}")
    public ProductResponseDto update(@PathVariable(name = "id") Long productId,
                                     @RequestBody ProductRequestDto productRequestDto) {
        Product product = productRequestDtoMapper.toModel(productRequestDto);
        product.setId(productId);
        product.setCategory(categoryService.save(product.getCategory()));
        return productResponseDtoMapper.toDto(productService.save(product));
    }

    @GetMapping("/by-price")
    public List<ProductResponseDto> findAll(@RequestParam BigDecimal from,
                                            @RequestParam BigDecimal to) {
        return productService.findAll(from, to).stream()
                .map(productResponseDtoMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/by-categories")
    public List<ProductResponseDto> findAll(@RequestParam Map<String, String> params) {
        return productService.findAll(params).stream()
                .map(productResponseDtoMapper::toDto)
                .collect(Collectors.toList());
    }
}
