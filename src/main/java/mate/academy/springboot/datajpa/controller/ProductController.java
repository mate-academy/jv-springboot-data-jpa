package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import mate.academy.springboot.datajpa.dto.request.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.response.ProductResponseDto;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.service.ProductService;
import mate.academy.springboot.datajpa.service.mapper.ProductDtoMapper;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ProductDtoMapper mapper;

    @PostMapping
    public ProductResponseDto save(
            @RequestParam ProductRequestDto requestDto) {
        return mapper.toDto(productService.save(mapper.toModel(requestDto)));
    }

    @GetMapping("/{id}")
    public ProductResponseDto get(@PathVariable Long id) {
        return mapper.toDto(productService.getById(id));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        productService.deleteById(id);
    }

    @PutMapping("/{id}")
    public ProductResponseDto update(@PathVariable Long id,
                                     @RequestParam ProductRequestDto requestDto) {
        Product product = mapper.toModel(requestDto);
        product.setId(id);
        return mapper.toDto(productService.save(product));
    }

    @GetMapping
    public List<ProductResponseDto> getAllByPriceBetween(BigDecimal priceFrom,
                                                         BigDecimal priceTo) {
        return productService
                .findAllByPriceBetween(priceFrom, priceTo)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping
    public List<ProductResponseDto> getAllByCategoryIds(
            @RequestParam List<Long> categoriesIds) {
        return productService
                .findAllByCategories(categoriesIds)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
}
