package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import mate.academy.springboot.datajpa.dto.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.ProductResponseDto;
import mate.academy.springboot.datajpa.mapper.DtoMapper;
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
@RequiredArgsConstructor
public class ProductController {
    private ProductService productService;
    private DtoMapper<ProductResponseDto, Product, ProductRequestDto> productMapper;

    @GetMapping("/{id}")
    public ProductResponseDto getById(@PathVariable Long id) {
        return productMapper.toDto(productService.getById(id));
    }

    @PostMapping
    public ProductResponseDto save(@RequestBody ProductRequestDto productRequestDto) {
        return productMapper.toDto(productService.add(productMapper.toModel(productRequestDto)));
    }

    @PutMapping("/{id}")
    public ProductResponseDto update(@RequestParam Long id,
                                     @RequestBody ProductRequestDto productRequestDto) {
        Product product = productMapper.toModel(productRequestDto);
        product.setId(id);
        return productMapper.toDto(productService.update(product));
    }

    @DeleteMapping("/{id}")
    public void delete(@RequestParam Long id) {
        productService.delete(id);
    }

    @GetMapping("/by-price")
    public List<ProductResponseDto> findProductByPriceBetween(@RequestParam BigDecimal from,
                                                              @RequestParam BigDecimal to) {
        return productService.findAllByPriceBetween(from, to).stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/by-categories")
    public List<ProductResponseDto> findProductByCategoryName(
            @RequestParam("categories") List<String> categories) {
        return productService.findAllByCategoryNameIn(categories).stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }
}
