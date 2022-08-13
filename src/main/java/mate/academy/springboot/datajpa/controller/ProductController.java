package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import mate.academy.springboot.datajpa.mapper.DtoRequestMapper;
import mate.academy.springboot.datajpa.mapper.DtoResponseMapper;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.model.dto.request.ProductRequestDto;
import mate.academy.springboot.datajpa.model.dto.response.ProductResponseDto;
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
    private final DtoRequestMapper<ProductRequestDto, Product> dtoRequestMapper;
    private final DtoResponseMapper<ProductResponseDto, Product> dtoResponseMapper;
    private final ProductService productService;

    @PostMapping
    public ProductResponseDto save(@RequestBody ProductRequestDto dto) {
        return dtoResponseMapper.toDto(productService.save(dtoRequestMapper.fromDto(dto)));
    }

    @GetMapping("/{id}")
    public ProductResponseDto getById(@PathVariable Long id) {
        return dtoResponseMapper.toDto(productService.getById(id));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        productService.deleteById(id);
    }

    @PutMapping("/{id}")
    public ProductResponseDto update(@PathVariable Long id, @RequestBody ProductRequestDto dto) {
        Product product = dtoRequestMapper.fromDto(dto);
        product.setId(id);
        return dtoResponseMapper.toDto(productService.save(product));
    }

    @GetMapping("/by-price")
    public List<ProductResponseDto> get(@RequestParam BigDecimal from, BigDecimal to) {
        return productService.getByPriceBetween(from, to)
                .stream()
                .map(dtoResponseMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/by-category")
    public List<ProductResponseDto> get(@RequestParam List<Long> categoryIds) {
        return productService.getByCategories(categoryIds)
                .stream()
                .map(dtoResponseMapper::toDto)
                .collect(Collectors.toList());
    }
}
