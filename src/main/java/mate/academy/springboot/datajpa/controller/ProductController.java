package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import mate.academy.springboot.datajpa.dto.request.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.response.ProductResponseDto;
import mate.academy.springboot.datajpa.entity.Product;
import mate.academy.springboot.datajpa.service.ProductService;
import mate.academy.springboot.datajpa.service.mapper.ProductMapper;
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
@AllArgsConstructor
public class ProductController {
    private ProductService productService;
    private ProductMapper productMapper;

    @PostMapping
    public ProductResponseDto save(@RequestBody ProductRequestDto requestDto) {
        Product product = productMapper.toEntity(requestDto);
        productService.save(product);
        return productMapper.toDto(product);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }

    @PutMapping("/{id}")
    public ProductResponseDto update(
            @RequestBody ProductRequestDto requestDto,
            @PathVariable Long id
    ) {
        Product product = productMapper.toEntity(requestDto);
        product.setId(id);
        productService.save(product);
        return productMapper.toDto(product);
    }

    @GetMapping("/{id}")
    public ProductResponseDto findById(@PathVariable Long id) {
        Product product = productService.findById(id);
        return productMapper.toDto(product);
    }

    @GetMapping("/by-price")
    public List<ProductResponseDto> findByPriceBetween(
            @RequestParam BigDecimal from,
            @RequestParam BigDecimal to
    ) {
        List<Product> products = productService.findAllByPriceBetween(from, to);
        return products.stream()
                .map(productMapper::toDto)
                .toList();
    }

    @GetMapping("/by-categories")
    public List<ProductResponseDto> findAllByCategoryNames(
            @RequestParam List<String> categoryNames
    ) {
        return productService.findAllByCategoryNames(categoryNames).stream()
                .map(productMapper::toDto)
                .toList();
    }
}
