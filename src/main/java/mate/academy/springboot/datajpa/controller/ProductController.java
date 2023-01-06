package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import mate.academy.springboot.datajpa.dto.request.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.response.ProductResponseDto;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.service.ProductService;
import mate.academy.springboot.datajpa.service.mapper.request.ProductRequestMapper;
import mate.academy.springboot.datajpa.service.mapper.response.ProductResponseMapper;
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
    private final ProductResponseMapper productResponseMapper;
    private final ProductRequestMapper productRequestMapper;
    private final ProductService productService;

    public ProductController(ProductResponseMapper productResponseMapper,
                             ProductRequestMapper productRequestMapper,
                             ProductService productService) {
        this.productResponseMapper = productResponseMapper;
        this.productRequestMapper = productRequestMapper;
        this.productService = productService;
    }

    @PostMapping
    public ProductResponseDto add(@RequestBody ProductRequestDto requestDto) {
        Product product = productService.save(productRequestMapper.mapToModel(requestDto));
        return productResponseMapper.mapToDto(product);
    }

    @GetMapping("/{id}")
    public ProductResponseDto get(@PathVariable Long id) {
        Product product = productService.getById(id);
        return productResponseMapper.mapToDto(product);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.deleteById(id);
    }

    @PutMapping("/{id}")
    public ProductResponseDto update(@PathVariable Long id,
                                     @RequestBody ProductRequestDto requestDto) {
        Product product = productRequestMapper.mapToModel(requestDto);
        product.setId(id);
        productService.update(product);
        return productResponseMapper.mapToDto(product);
    }

    @GetMapping("/by-price")
    public List<ProductResponseDto> getByPrice(@RequestParam BigDecimal from,
                                               @RequestParam BigDecimal to) {
        return productService.findAllByPriceBetween(from, to).stream()
                .map(productResponseMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/by-categories")
    public List<ProductResponseDto> getByCategories(@RequestParam
                                                       Map<String, String> params) {
        return productService.findAllByCategory(params).stream()
                .map(productResponseMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
