package mate.academy.springboot.datajpa.controllers;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Data;
import mate.academy.springboot.datajpa.dto.request.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.response.ProductResponseDto;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.service.ProductService;
import mate.academy.springboot.datajpa.service.mapper.RequestDtoMapper;
import mate.academy.springboot.datajpa.service.mapper.ResponseDtoMapper;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Data
@RestController
@RequestMapping("/products")
public class ProductController {
    private final RequestDtoMapper<ProductRequestDto, Product> requestDtoMapper;
    private final ResponseDtoMapper<ProductResponseDto, Product> responseDtoMapper;
    private final ProductService productService;

    @PostMapping
    public ProductResponseDto create(@RequestBody ProductRequestDto productRequestDto) {
        Product product = productService.save(requestDtoMapper.toModel(productRequestDto));
        return responseDtoMapper.toDto(product);
    }

    @GetMapping("/{id}")
    public ProductResponseDto get(@PathVariable Long id) {
        return responseDtoMapper.toDto(productService.getById(id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }

    @PutMapping("/{id}")
    public ProductResponseDto update(@PathVariable Long id,
                                     @RequestBody ProductRequestDto productRequestDto) {
        Product product = requestDtoMapper.toModel(productRequestDto);
        product.setId(id);
        return responseDtoMapper.toDto(productService.save(product));
    }

    @GetMapping("/by-price")
    public List<ProductResponseDto> getByPriceBetween(@RequestParam BigDecimal fromPrice,
                                                      @RequestParam BigDecimal toPrice) {
        return productService.findAllByPriceBetween(fromPrice, toPrice).stream()
                .map(responseDtoMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/by-category")
    public List<ProductResponseDto> getByCategory(@RequestParam List<Long> categoryIds) {
        return productService.findAllByCategory(categoryIds).stream()
                .map(responseDtoMapper::toDto)
                .collect(Collectors.toList());
    }
}
