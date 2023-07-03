package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import mate.academy.springboot.datajpa.dto.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.ProductResponseDto;
import mate.academy.springboot.datajpa.dto.mapper.ProductMapper;
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

    private final ProductMapper productMapper;

    public ProductController(ProductService productService,
                             ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;;
    }

    @GetMapping("/{id}")
    public ProductResponseDto getById(@PathVariable Long id) {
        return productMapper.toDto(productService.get(id));
    }

    @GetMapping("/price-between")
    public List<ProductResponseDto> getAll(@RequestParam BigDecimal from,
                                           @RequestParam BigDecimal to) {
        return productService.findAllByPriceBetween(from, to).stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/category")
    public List<ProductResponseDto> byCategoryId(@RequestParam Set<String> categories) {
        return productService.getAllProductsFromCategory(categories).stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    public ProductResponseDto save(@RequestBody ProductRequestDto productRequestDto) {
        Product save = productService.save(productMapper.toModel(productRequestDto));
        return productMapper.toDto(save);
    }

    @PutMapping("/{id}")
    public ProductResponseDto update(@PathVariable Long id,
                                     @RequestBody ProductRequestDto productRequestDto) {
        Product model = productMapper.toModel(productRequestDto);
        model.setId(id);
        return productMapper.toDto(productService.update(model));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }
}
