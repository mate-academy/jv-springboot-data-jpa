package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.model.dto.ProductRequestDto;
import mate.academy.springboot.datajpa.model.dto.ProductResponseDto;
import mate.academy.springboot.datajpa.service.CategoryService;
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
    private final DtoMapper<Product, ProductResponseDto, ProductRequestDto> productMapper;

    public ProductController(CategoryService categoryService,
                             ProductService productService, DtoMapper<Product, ProductResponseDto,
            ProductRequestDto> productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @PostMapping
    public ProductResponseDto create(@RequestBody ProductRequestDto requestDto) {
        return productMapper.toDto(productService.create(productMapper.toEntity(requestDto)));
    }

    @GetMapping("/{id}")
    public ProductResponseDto get(@PathVariable Long id) {
        return productMapper.toDto(productService.get(id));
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }

    @PutMapping("/{id}")
    public ProductResponseDto update(@PathVariable Long id,
                                     @RequestBody ProductRequestDto requestDto) {
        Product updatedProduct = productMapper.toEntity(requestDto);
        updatedProduct.setId(id);
        return productMapper.toDto(productService.update(updatedProduct));
    }

    @GetMapping("/get-by-price")
    public List<ProductResponseDto> getAllPriceBetween(@RequestParam BigDecimal from,
                                                       @RequestParam BigDecimal to) {
        return productService.getAllBetweenPrice(from, to)
                .stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/get-by-categories")
    public List<ProductResponseDto> getAllByCategories(@RequestParam List<String> categories) {
        return productService.findAllByCategoryIn(categories)
                .stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }
}
