package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.model.dto.request.ProductRequestDto;
import mate.academy.springboot.datajpa.model.dto.response.ProductResponseDto;
import mate.academy.springboot.datajpa.service.ProductService;
import mate.academy.springboot.datajpa.service.mapper.DtoMapper;
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
@AllArgsConstructor
public class ProductController {
    private ProductService productService;
    private DtoMapper<Product, ProductResponseDto, ProductRequestDto> mapper;

    @GetMapping("/{id}")
    public ProductResponseDto findById(@PathVariable Long id) {
        return mapper.toDto(productService.findById(id));
    }

    @GetMapping("/price-between")
    public Set<ProductResponseDto> findByPriceBetween(@RequestParam BigDecimal from,
                                                      @RequestParam BigDecimal to) {
        return productService.findProductByPriceBetween(from, to)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toSet());
    }

    @GetMapping
    public List<ProductResponseDto> findAll() {
        return productService.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/category")
    public List<ProductResponseDto> findAll(@RequestParam("categories") Set<String> categoryNames) {
        return productService.findAll(categoryNames)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    public ProductResponseDto create(@RequestBody ProductRequestDto dto) {
        Product product = mapper.toModel(dto);
        return mapper.toDto(productService.save(product));
    }

    @PutMapping("/{id}")
    public void update(@RequestBody ProductRequestDto dto, @PathVariable Long id) {
        Product product = mapper.toModel(dto);
        product.setId(id);
        productService.update(product);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }
}
