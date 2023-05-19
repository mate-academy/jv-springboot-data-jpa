package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import mate.academy.springboot.datajpa.dto.mapper.ProductMapper;
import mate.academy.springboot.datajpa.dto.requestdto.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.responsedto.ProductResponseDto;
import mate.academy.springboot.datajpa.model.Category;
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
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ProductMapper productMapper;

    @GetMapping("/{id}")
    public ProductResponseDto get(@PathVariable Long id) {
        return productMapper.toDto(productService.findById(id));
    }

    @PostMapping
    public void create(@RequestBody ProductRequestDto productRequestDto) {
        productService.create(productMapper.toModel(productRequestDto));
    }

    @PutMapping
    public ProductResponseDto update(@RequestBody ProductRequestDto productRequestDto) {
        return productMapper.toDto(
                productService.update(
                        productMapper.toModel(productRequestDto)));
    }

    @GetMapping
    public List<ProductResponseDto> getAll() {
        return productService.findAll()
                .stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/price")
    public List<ProductResponseDto> getByPrice(
            @RequestParam BigDecimal from,
            @RequestParam BigDecimal to) {
        return productService.findAllByPriceBetween(from, to)
                .stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/categories")
    public List<ProductResponseDto> getByCategory(@RequestParam List<Category> categories) {
        return productService.findAllByCategory(categories)
                .stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.deleteById(id);
    }
}
