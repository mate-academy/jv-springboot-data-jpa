package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import mate.academy.springboot.datajpa.dto.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.ProductResponseDto;
import mate.academy.springboot.datajpa.mapper.DtoMapper;
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
    private final DtoMapper<Product, ProductRequestDto, ProductResponseDto> dtoMapper;

    @Autowired
    public ProductController(
            ProductService productService,
            DtoMapper<Product, ProductRequestDto, ProductResponseDto> dtoMapper) {
        this.productService = productService;
        this.dtoMapper = dtoMapper;
    }

    @PostMapping
    public ProductResponseDto create(@RequestBody ProductRequestDto requestDto) {
        Product product = productService.save(dtoMapper.mapToModel(requestDto));
        return dtoMapper.mapToDto(product);
    }

    @GetMapping("/{id}")
    public ProductResponseDto getById(@PathVariable Long id) {
        return dtoMapper.mapToDto(productService.findById(id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.deleteById(id);
    }

    @PutMapping("/{id}")
    public ProductResponseDto update(@PathVariable Long id,
                                     @RequestBody ProductRequestDto requestDto) {
        Product product = dtoMapper.mapToModel(requestDto);
        product.setId(id);
        return dtoMapper.mapToDto(productService.update(product));
    }

    @GetMapping("/price-between")
    public List<ProductResponseDto> findAllByPriceBetween(@RequestParam BigDecimal from,
                                                          @RequestParam BigDecimal to) {
        return productService.findAllByPriceBetween(from, to).stream()
                .map(dtoMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/by-category")
    public List<ProductResponseDto> findAllByCategory(@RequestParam Long id) {
        return productService.findAllByCategory(id).stream()
                .map(dtoMapper::mapToDto)
                .toList();
    }
}
