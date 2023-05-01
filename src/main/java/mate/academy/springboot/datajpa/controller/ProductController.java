package mate.academy.springboot.datajpa.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import mate.academy.springboot.datajpa.dto.request.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.response.ProductResponseDto;
import mate.academy.springboot.datajpa.mapper.RequestDtoMapper;
import mate.academy.springboot.datajpa.mapper.ResponseDtoMapper;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final ResponseDtoMapper<Product, ProductResponseDto> productResponseMapper;
    private final RequestDtoMapper<Product, ProductRequestDto> productRequestMapper;

    @Autowired
    public ProductController(ProductService productService,
                             ResponseDtoMapper<Product, ProductResponseDto> productResponseMapper,
                             RequestDtoMapper<Product, ProductRequestDto> productRequestMapper) {
        this.productService = productService;
        this.productResponseMapper = productResponseMapper;
        this.productRequestMapper = productRequestMapper;
    }

    @PostMapping
    public ProductResponseDto create(@RequestBody ProductRequestDto productRequestDto) {
        Product product = productRequestMapper.toModel(productRequestDto);
        productService.save(product);
        return productResponseMapper.toDto(product);
    }

    @GetMapping("/{id}")
    public ProductResponseDto get(@PathVariable Long id) {
        return productResponseMapper.toDto(productService.get(id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }

    @PutMapping("/{id}")
    public ProductResponseDto update(@RequestBody ProductRequestDto productRequestDto,
                                     @PathVariable Long id) {
        Product product = productRequestMapper.toModel(productRequestDto);
        product.setId(id);
        return productResponseMapper.toDto(productService.update(product));
    }

    @GetMapping
    public List<ProductResponseDto> getAll(@RequestParam Map<String, String> params) {
        return productService.getAll(params).stream()
                .map(productResponseMapper::toDto)
                .collect(Collectors.toList());
    }
}
