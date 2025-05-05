package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import mate.academy.springboot.datajpa.dto.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.ProductResponseDto;
import mate.academy.springboot.datajpa.dto.mapper.DtoMapper;
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

    private final DtoMapper<Product, ProductRequestDto, ProductResponseDto> productMapper;

    public ProductController(ProductService productService,
                    DtoMapper<Product, ProductRequestDto, ProductResponseDto> productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @GetMapping("/{id}")
    public ProductResponseDto getById(@PathVariable("id") Long id) {
        return productMapper.toResponseDto(productService.findById(id));
    }

    @GetMapping
    public List<ProductResponseDto> getAll(@RequestParam Map<String, String> params) {
        return productService.findAll(params)
                             .stream()
                             .map(productMapper::toResponseDto)
                             .collect(Collectors.toList());
    }

    @GetMapping("/price")
    public List<ProductResponseDto> getAllByPriceBetween(@RequestParam BigDecimal from,
                                                     @RequestParam BigDecimal to) {
        return productService.findAllByPriceBetween(from, to)
                             .stream()
                             .map(productMapper::toResponseDto)
                             .collect(Collectors.toList());
    }

    @PostMapping
    public ProductResponseDto create(@RequestBody ProductRequestDto requestDto) {
        Product product = productService.save(productMapper.toModel(requestDto));
        return productMapper.toResponseDto(product);
    }

    @PutMapping("/{id}")
    public ProductResponseDto update(@PathVariable("id") Long id,
                                      @RequestBody ProductRequestDto requestDto) {
        return productMapper.toResponseDto(productService
                                            .update(id, productMapper.toModel(requestDto)));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        productService.delete(id);
    }
}
