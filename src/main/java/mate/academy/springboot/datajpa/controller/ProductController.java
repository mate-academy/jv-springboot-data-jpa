package mate.academy.springboot.datajpa.controller;

import java.util.List;
import java.util.stream.Collectors;
import mate.academy.springboot.datajpa.dto.CategoryResponseDto;
import mate.academy.springboot.datajpa.dto.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.ProductResponseDto;
import mate.academy.springboot.datajpa.dto.mapper.DtoRequestMapper;
import mate.academy.springboot.datajpa.dto.mapper.DtoResponseMapper;
import mate.academy.springboot.datajpa.model.Category;
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
    private final DtoRequestMapper<ProductRequestDto, Product> productRequestMapper;
    private final DtoResponseMapper<ProductResponseDto, Product> productResponseMapper;
    private final DtoResponseMapper<CategoryResponseDto, Category> categoryResponseMapper;

    public ProductController(ProductService productService,
                             DtoRequestMapper<ProductRequestDto, Product>
                                     productRequestMapper,
                             DtoResponseMapper<ProductResponseDto, Product>
                                     productResponseMapper,
                             DtoResponseMapper<CategoryResponseDto, Category>
                                     categoryResponseMapper) {
        this.productService = productService;
        this.productRequestMapper = productRequestMapper;
        this.productResponseMapper = productResponseMapper;
        this.categoryResponseMapper = categoryResponseMapper;
    }

    @PostMapping
    public ProductResponseDto create(@RequestBody ProductRequestDto requestDto) {
        Product product = productService.save(productRequestMapper.fromDto(requestDto));
        return productResponseMapper.toDto(product);
    }

    @GetMapping
    public ProductResponseDto getById(@RequestParam Long id) {
        return productResponseMapper.toDto(productService.get(id)
                .orElseThrow(RuntimeException::new));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.deleteById(id);
    }

    @PutMapping("/{id}")
    public ProductResponseDto update(@PathVariable Long id,
                                     @RequestBody ProductRequestDto requestDto) {
        Product product = productRequestMapper.fromDto(requestDto);
        product.setId(id);
        productService.update(product);
        return productResponseMapper.toDto(product);
    }

    @GetMapping("/price-between")
    public List<ProductResponseDto> getAllPriceBetween(@RequestParam int from,
                                                       @RequestParam int to) {
        List<Product> allByPriceBetween = productService.findAllByPriceBetween(from, to);
        return allByPriceBetween.stream()
                .map(productResponseMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/all-in-categories")
    public List<CategoryResponseDto> getAllInCategories(@RequestParam List<Long> categoriesIds) {
        List<Category> allInCategories = productService.findAllInCategories(categoriesIds);
        return allInCategories.stream()
                .map(categoryResponseMapper::toDto)
                .collect(Collectors.toList());
    }
}
