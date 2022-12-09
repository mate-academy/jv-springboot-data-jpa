package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import mate.academy.springboot.datajpa.dto.mapper.ProductDtoMapper;
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
public class ProductController {
    private final ProductService productService;
    private final ProductDtoMapper productDtoMapper;

    public ProductController(ProductService productService, ProductDtoMapper productDtoMapper) {
        this.productService = productService;
        this.productDtoMapper = productDtoMapper;
    }

    @PostMapping
    public ProductResponseDto create(@RequestBody ProductRequestDto productRequestDto) {
        return productDtoMapper.toDto(productService
                .save(productDtoMapper.fromDto(productRequestDto)));
    }

    @GetMapping("/{id}")
    public ProductResponseDto get(@PathVariable Long id) {
        return productDtoMapper.toDto(productService.get(id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }

    @PutMapping("/{id}")
    public ProductResponseDto update(@PathVariable Long id,
                                     @RequestBody ProductRequestDto productRequestDto) {
        Product product = productDtoMapper.fromDto(productRequestDto);
        product.setId(id);
        return productDtoMapper.toDto(productService.save(product));
    }

    @GetMapping("/price")
    public List<ProductResponseDto> getByPriceBetween(@RequestParam BigDecimal from,
                                                      @RequestParam BigDecimal to) {
        return productService.getAllByPriceBetween(from,to)
                .stream()
                .map(productDtoMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/categories")
    public List<ProductResponseDto> getAllByCategoryIdIn(@RequestParam List<Long> categories) {
        return productService.getAllByCategoryIdIn(categories)
                .stream()
                .map(productDtoMapper::toDto)
                .collect(Collectors.toList());
    }
}
