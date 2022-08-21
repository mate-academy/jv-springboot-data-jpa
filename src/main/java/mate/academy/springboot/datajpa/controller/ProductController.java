package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import mate.academy.springboot.datajpa.dto.request.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.response.ProductResponseDto;
import mate.academy.springboot.datajpa.mapper.ProductRequestMapper;
import mate.academy.springboot.datajpa.mapper.ProductResponseDtoMapper;
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
    private ProductService productService;
    private ProductResponseDtoMapper productResponseDtoMapper;
    private ProductRequestMapper requestMapper;

    public ProductController(ProductService productService,
                             ProductResponseDtoMapper productResponseDtoMapper,
                             ProductRequestMapper requestMapper) {
        this.productService = productService;
        this.productResponseDtoMapper = productResponseDtoMapper;
        this.requestMapper = requestMapper;
    }

    @GetMapping
    public List<ProductResponseDto> getAll(@RequestParam Map<String, String> params) {
        return productService.findAll(params).stream()
                .map(productResponseDtoMapper::toDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    public ProductResponseDto create(@RequestBody ProductRequestDto requestDto) {
        return productResponseDtoMapper.toDto(
                productService.create(requestMapper.toModel(requestDto)));
    }

    @PutMapping("/{id}")
    public ProductResponseDto update(@PathVariable Long id,
                                     @RequestBody ProductRequestDto requestDto) {
        Product product = requestMapper.toModel(requestDto);
        product.setId(id);
        return productResponseDtoMapper.toDto(productService.create(product));
    }

    @GetMapping("/id")
    public ProductResponseDto getById(@PathVariable Long id) {
        return productResponseDtoMapper.toDto(productService.getById(id));
    }

    @GetMapping("/priceBetween")
    public List<ProductResponseDto> get(@RequestParam BigDecimal from, BigDecimal to) {
        return productService.getAllProductsWherePriceBetween(from, to)
                .stream()
                .map(productResponseDtoMapper::toDto)
                .collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        productService.delete(id);
    }
}
