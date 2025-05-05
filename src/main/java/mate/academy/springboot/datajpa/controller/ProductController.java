package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.springboot.datajpa.dto.request.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.response.ProductResponseDto;
import mate.academy.springboot.datajpa.mapper.DtoMapper;
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

@RequiredArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final DtoMapper<Product, ProductRequestDto, ProductResponseDto> dtoMapper;

    @PostMapping
    ProductResponseDto create(@RequestBody ProductRequestDto reqDto) {
        Product product = dtoMapper.toModel(reqDto);
        return dtoMapper.toDto(productService.save(product));
    }

    @GetMapping("/{id}")
    ProductResponseDto getById(@PathVariable Long id) {
        return dtoMapper.toDto(productService.getById(id));
    }

    @DeleteMapping("/{id}")
    boolean deleteById(@PathVariable Long id) {
        return productService.deleteById(id);
    }

    @PutMapping("/{id}")
    ProductResponseDto update(@PathVariable Long id, @RequestBody ProductRequestDto reqDto) {
        Product product = dtoMapper.toModel(reqDto);
        product.setId(id);
        return dtoMapper.toDto(productService.update(id, product));
    }

    @GetMapping("/by-price")
    List<ProductResponseDto> getByPriceBetween(
            @RequestParam BigDecimal from,
            @RequestParam BigDecimal to
    ) {
        return productService.getByPriceBetween(from, to).stream()
                .map(dtoMapper::toDto)
                .toList();
    }

    @PostMapping("/by-categories")
    List<ProductResponseDto> getByCategories(@RequestBody List<Long> categoryIds) {
        return productService.getByCategoryIds(categoryIds).stream()
                .map(dtoMapper::toDto)
                .toList();
    }
}
