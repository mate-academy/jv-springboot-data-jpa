package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import java.util.List;
import mate.academy.springboot.datajpa.dto.request.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.response.ProductResponseDto;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.service.ProductService;
import mate.academy.springboot.datajpa.service.mapper.DtoMapper;
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
    private final DtoMapper<Product, ProductRequestDto, ProductResponseDto> mapper;

    public ProductController(ProductService productService,
                             DtoMapper<Product, ProductRequestDto, ProductResponseDto> mapper) {
        this.productService = productService;
        this.mapper = mapper;
    }

    @PostMapping
    public ProductResponseDto create(@RequestBody ProductRequestDto productRequestDto) {
        return mapper.toDto(productService.create(mapper.toModel(productRequestDto)));
    }

    @GetMapping("/{id}")
    public ProductResponseDto getById(@PathVariable Long id) {
        return mapper.toDto(productService.getById(id));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        productService.deleteById(id);
    }

    @PutMapping("/{id}")
    public ProductResponseDto update(@PathVariable Long id,
                                     @RequestBody ProductRequestDto productRequestDto) {
        Product product = mapper.toModel(productRequestDto);
        product.setId(id);
        return mapper.toDto(productService.update(product));
    }

    @GetMapping("by-price")
    public List<ProductResponseDto> getAllBetween(@RequestParam BigDecimal from,
                                                  @RequestParam BigDecimal to) {
        return productService.getAllProductsByPriceBetween(from, to)
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @GetMapping("/by-categories")
    public List<ProductResponseDto> getAllByCategories(@RequestParam List<Long> categoryIds) {
        return productService.getAllProductsByCategories(categoryIds).stream()
                .map(mapper::toDto)
                .toList();
    }
}
