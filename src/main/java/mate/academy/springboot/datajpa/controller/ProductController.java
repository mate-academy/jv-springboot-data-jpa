package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import mate.academy.springboot.datajpa.dto.request.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.response.ProductResponseDto;
import mate.academy.springboot.datajpa.mapper.ProductDtoMapper;
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
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final ProductDtoMapper productDtoMapper;

    @PostMapping
    ProductResponseDto save(@RequestBody ProductRequestDto productRequestDto) {
        return productDtoMapper.toDto(productService
                .save(productDtoMapper.toProduct(productRequestDto)));
    }

    @PutMapping("/{id}")
    ProductResponseDto update(@PathVariable Long id,
                              @RequestBody ProductRequestDto productRequestDto) {
        Product product = productDtoMapper.toProduct(productRequestDto);
        product.setId(id);
        return productDtoMapper.toDto(productService.save(product));
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id) {
        productService.deleteById(id);
    }

    @GetMapping("/{id}")
    ProductResponseDto getById(@PathVariable Long id) {
        return productDtoMapper.toDto(productService.getById(id));
    }

    @GetMapping("/price")
    List<ProductResponseDto> getAllByPriceBetween(@RequestParam BigDecimal from,
                                                  @RequestParam BigDecimal to) {
        return productService.findAllByPriceBetween(from, to).stream()
                .map(productDtoMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/categories")
    List<ProductResponseDto> getAllByCategory(@RequestParam Map<String, String> params) {
        return productService.findAll(params).stream()
                .map(productDtoMapper::toDto)
                .collect(Collectors.toList());
    }
}
