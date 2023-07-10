package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import java.util.List;
import mate.academy.springboot.datajpa.dto.mapper.ProductRequestMapper;
import mate.academy.springboot.datajpa.dto.mapper.ProductResponseMapper;
import mate.academy.springboot.datajpa.dto.request.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.response.ProductResponseDto;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.service.CategoryService;
import mate.academy.springboot.datajpa.service.ProductService;
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
    private final ProductRequestMapper productRequestMapper;
    private final ProductResponseMapper productResponseMapper;

    public ProductController(ProductService productService,
                             CategoryService categoryService,
                             ProductRequestMapper productRequestMapper,
                             ProductResponseMapper productResponseMapper) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.productRequestMapper = productRequestMapper;
        this.productResponseMapper = productResponseMapper;
    }

    @PostMapping
    public ProductResponseDto create(@RequestBody ProductRequestDto dto) {
        Product product = productService.save(productRequestMapper.mapToModel(dto));
        return productResponseMapper.mapToDto(product);

    }

    @GetMapping("/{id}")
    public ProductResponseDto get(@PathVariable Long id) {
        Product product = productService.get(id);
        return productResponseMapper.mapToDto(product);
    }

    @PutMapping("/{id}")
    public ProductResponseDto update(@PathVariable Long id,
                                     @RequestBody ProductRequestDto dto) {
        Product product = productRequestMapper.mapToModel(dto);
        product.setId(id);
        return productResponseMapper.mapToDto(productService.save(product));
    }

    @PostMapping("/{id}")
    public void delete(Long id) {

        productService.delete(id);
    }

    @GetMapping("/by-price")
    public List<ProductResponseDto> findAllBetweenPrices(@RequestParam BigDecimal from,
                                                         @RequestParam BigDecimal to) {
        return productService.findAllBetweenTwoPrices(from, to).stream()
                .map(productResponseMapper::mapToDto)
                .toList();
    }

    @GetMapping("/by-categories")
    public List<ProductResponseDto> getAllInCategories(@RequestParam List<String> categoryNames) {
        return productService.getAllInCategories(categoryNames).stream()
                .map(productResponseMapper::mapToDto)
                .toList();
    }
}
