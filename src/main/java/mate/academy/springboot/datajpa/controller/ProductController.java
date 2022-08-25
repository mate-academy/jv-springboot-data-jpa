package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import mate.academy.springboot.datajpa.dto.request.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.response.ProductResponseDto;
import mate.academy.springboot.datajpa.mapper.request.DtoRequestMapper;
import mate.academy.springboot.datajpa.mapper.request.ProductRequestMapper;
import mate.academy.springboot.datajpa.mapper.response.DtoResponseMapper;
import mate.academy.springboot.datajpa.mapper.response.ProductResponseMapper;
import mate.academy.springboot.datajpa.model.Category;
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
    private final DtoResponseMapper<Product, ProductResponseDto> responseMapper;
    private final DtoRequestMapper<ProductRequestDto, Product> requestMapper;

    public ProductController(ProductService productService,
                             ProductResponseMapper responseMapper,
                             ProductRequestMapper requestMapper) {
        this.productService = productService;
        this.responseMapper = responseMapper;
        this.requestMapper = requestMapper;
    }

    @PostMapping
    public ProductResponseDto create(@RequestBody ProductRequestDto requestDto) {
        Product product = productService.save(requestMapper.toModel(requestDto));
        return responseMapper.toDto(product);
    }

    @GetMapping("/{id}")
    public ProductResponseDto getById(@PathVariable Long id) {
        Product product = productService.getById(id);
        return responseMapper.toDto(product);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        productService.deleteById(id);
    }

    @PutMapping("/{id}")
    public ProductResponseDto update(@PathVariable Long id,
                                    @RequestBody ProductRequestDto requestDto) {
        Product product = requestMapper.toModel(requestDto);
        product.setId(id);
        return responseMapper.toDto(productService.save(product));
    }

    @GetMapping("/by-price")
    public List<ProductResponseDto> getAllByPriceBetween(@RequestParam BigDecimal from,
                                                        @RequestParam BigDecimal to) {
        return productService.getAllByPriceBetween(from, to).stream()
                .map(responseMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/by-category")
    public List<ProductResponseDto> getAllByCategory(@RequestParam List<Long> categoriesId) {
        return productService.getAllByCategory(categoriesId).stream()
                .map(responseMapper::toDto)
                .collect(Collectors.toList());
    }
}
