package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import mate.academy.springboot.datajpa.dto.mapper.RequestDtoMapper;
import mate.academy.springboot.datajpa.dto.mapper.ResponseDtoMapper;
import mate.academy.springboot.datajpa.dto.request.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.response.ProductResponseDto;
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
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final RequestDtoMapper<ProductRequestDto, Product> requestDtoMapper;
    private final ResponseDtoMapper<ProductResponseDto, Product> responseDtoMapper;

    @PostMapping
    public ProductResponseDto create(@RequestBody ProductRequestDto productRequestDto) {
        Product product = productService.create(requestDtoMapper.toModel(productRequestDto));
        return responseDtoMapper.toDto(product);
    }

    @GetMapping("{id}")
    public ProductResponseDto get(@PathVariable Long id) {
        Product product = productService.get(id);
        return responseDtoMapper.toDto(product);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }

    @PutMapping("{id}")
    public ProductResponseDto update(@PathVariable Long id,
                                     @RequestBody ProductRequestDto productRequestDto) {
        Product product = requestDtoMapper.toModel(productRequestDto);
        product.setId(id);
        return responseDtoMapper.toDto(productService.create(product));
    }

    @GetMapping("/by-price")
    public List<ProductResponseDto> getAllProductsByPriceBetween(@RequestParam BigDecimal from,
                                                                 @RequestParam BigDecimal to) {
        return productService.findAllByPriceBetween(from, to)
                .stream()
                .map(responseDtoMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/by-category")
    public List<ProductResponseDto> getAllByCategory(@RequestParam List<String> categories) {
        return productService.findAllByCategoryNameIn(categories)
                .stream()
                .map(responseDtoMapper::toDto)
                .collect(Collectors.toList());
    }
}
