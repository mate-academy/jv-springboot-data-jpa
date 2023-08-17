package mate.academy.springboot.datajpa.controller;

import jakarta.validation.Valid;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import mate.academy.springboot.datajpa.dto.request.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.response.ProductResponseDto;
import mate.academy.springboot.datajpa.mapper.RequestDtoMapper;
import mate.academy.springboot.datajpa.mapper.ResponseDtoMapper;
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
    private final ResponseDtoMapper<ProductResponseDto, Product> responseDtoMapper;
    private final RequestDtoMapper<ProductRequestDto, Product> requestDtoMapper;

    public ProductController(ProductService productService,
                             ResponseDtoMapper<ProductResponseDto, Product> responseDtoMapper,
                             RequestDtoMapper<ProductRequestDto, Product> requestDtoMapper) {
        this.productService = productService;
        this.responseDtoMapper = responseDtoMapper;
        this.requestDtoMapper = requestDtoMapper;
    }

    @PostMapping
    public ProductResponseDto create(@RequestBody @Valid ProductRequestDto productRequestDto) {
        Product product = productService.create(requestDtoMapper.mapToModel(productRequestDto));
        return responseDtoMapper.mapToDto(product);
    }

    @GetMapping("/{id}")
    public ProductResponseDto getById(@PathVariable Long id) {
        Product product = productService.getById(id);
        return responseDtoMapper.mapToDto(product);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }

    @PutMapping("/{id}")
    public ProductResponseDto update(@PathVariable Long id,
                                     @RequestBody @Valid ProductRequestDto productRequestDto) {
        Product product = productService.update(requestDtoMapper
                .mapToModel(productRequestDto), id);
        return responseDtoMapper.mapToDto(product);
    }

    @GetMapping("/price")
    public List<ProductResponseDto> getAllByPriceBetween(@RequestParam BigDecimal from,
                                                         @RequestParam BigDecimal to) {
        List<Product> products = productService.getAllByPriceBetween(from, to);
        return products.stream()
                .map(responseDtoMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/category")
    public List<ProductResponseDto> getAllByCategories(@RequestParam List<String> categoryNames) {
        List<Product> products = productService.getAllByCategoryNames(categoryNames);
        return products.stream()
                .map(responseDtoMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
