package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import mate.academy.springboot.datajpa.dto.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.ProductResponseDto;
import mate.academy.springboot.datajpa.mapper.ProductDtoMapper;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.service.CategoryService;
import mate.academy.springboot.datajpa.service.ProductService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;
    private CategoryService categoryService;
    private ProductDtoMapper productDtoMapper;

    public ProductController(ProductService productService,
                             CategoryService categoryService, ProductDtoMapper productDtoMapper) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.productDtoMapper = productDtoMapper;
    }

    @PostMapping
    public ProductResponseDto save(@RequestParam ProductRequestDto productRequestDto) {
        Product product = productDtoMapper.toModel(productRequestDto);
        return productDtoMapper.mapToDto(productService.save(product));
    }

    @GetMapping("/{id}")
    public ProductResponseDto get(@PathVariable Long id) {
        return productDtoMapper.mapToDto(productService.getById(id));
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id) {
        productService.deleteById(id);
    }

    @PutMapping("/{id}")
    public ProductResponseDto update(@PathVariable Long id,
                                     @RequestParam ProductRequestDto requestDto) {
        Product product = productService.getById(id);
        product.setTitle(requestDto.getTitle());
        product.setPrice(requestDto.getPrice());
        product.setCategory(categoryService.getById(requestDto.getCategoryId()));
        return productDtoMapper.mapToDto(productService.save(product));
    }

    @GetMapping("/by-price")
    public List<ProductResponseDto> findAllByPriceBetween(BigDecimal from, BigDecimal to) {
        return productService.getAllByPriceBetween(from, to).stream()
               .map(p -> productDtoMapper.mapToDto(p))
               .collect(Collectors.toList());
    }

    @GetMapping("/by-categories")
    public List<ProductResponseDto> findAllByCategories(@RequestParam Map<String, String> params) {
        return productService.getAllByCategories(params)
                .stream()
                .map(p -> productDtoMapper.mapToDto(p))
                .collect(Collectors.toList());
    }
}
