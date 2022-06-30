package mate.academy.springboot.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import mate.academy.springboot.dto.ProductRequestDto;
import mate.academy.springboot.dto.ProductResponseDto;
import mate.academy.springboot.mapper.ProductMapper;
import mate.academy.springboot.model.Product;
import mate.academy.springboot.service.ProductService;
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
    private final ProductMapper productMapper;

    public ProductController(ProductService productService, ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @GetMapping("/{id}")
    public ProductResponseDto get(@PathVariable Long id) {
        return productMapper.mapToDto(productService.get(id));
    }

    @PostMapping
    public ProductResponseDto save(@RequestBody ProductRequestDto requestDto) {
        return productMapper.mapToDto(productService
                    .add(productMapper.mapToModel(requestDto)));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }

    @PutMapping("/{id}")
    public ProductResponseDto update(@PathVariable Long id,
                                         @RequestBody ProductRequestDto requestDto) {
        Product product = productMapper.mapToModel(requestDto);
        product.setId(id);
        return productMapper.mapToDto(productService.update(product));
    }

    @GetMapping("/by-price")
    public List<ProductResponseDto> getAllByPriceBetween(@RequestParam BigDecimal from,
                                                             @RequestParam BigDecimal to) {
        return productService.getBetweenPrice(from, to).stream()
                    .map(productMapper::mapToDto)
                    .collect(Collectors.toList());
    }

    @GetMapping("/by-category")
    public List<ProductResponseDto> getAllByCategoryIn(@RequestParam List<Long> categoryIds) {
        return productService.getByCategories(categoryIds).stream()
                    .map(productMapper::mapToDto)
                    .collect(Collectors.toList());
    }
}
