package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import mate.academy.springboot.datajpa.dto.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.ProductResponseDto;
import mate.academy.springboot.datajpa.dto.mapper.DtoMapper;
import mate.academy.springboot.datajpa.dto.mapper.ProductMapper;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.service.ProductService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final DtoMapper<Product, ProductRequestDto, ProductResponseDto> productMapper;

    public ProductController(ProductService productService, ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @PostMapping
    public ProductResponseDto save(@RequestBody ProductRequestDto productRequestDto) {
        return productMapper.toDto(
                productService.save(productMapper.toModel(productRequestDto)));
    }

    @PostMapping("/update")
    public ProductResponseDto update(@RequestParam Long id,
                                     @RequestBody ProductRequestDto productRequestDto) {
        Product product = productMapper.toModel(productRequestDto);
        product.setId(id);
        return productMapper.toDto(
                productService.save(product));
    }

    @GetMapping
    public ProductResponseDto getById(@RequestParam Long id) {
        return productMapper.toDto(productService.getByID(id));
    }

    @DeleteMapping
    public void deleteById(@RequestParam Long id) {
        productService.deleteById(id);
    }

    @GetMapping("/by-price")
    public List<ProductResponseDto> getAllByPriceBetween(@RequestParam Long from,
                                                         @RequestParam Long to) {
        return productService.findAllByPriceBetween(BigDecimal.valueOf(from),
                        BigDecimal.valueOf(to))
                .stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/in-categories")
    public List<ProductResponseDto> findAllByCategoryIn(@RequestParam List<Long> ids) {
        return productService.findAllByCategoryIn(ids)
                .stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }
}
