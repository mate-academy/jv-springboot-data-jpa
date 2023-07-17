package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import mate.academy.springboot.datajpa.dto.mapper.DtoMapper;
import mate.academy.springboot.datajpa.dto.request.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.response.ProductResponseDto;
import mate.academy.springboot.datajpa.model.Category;
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
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final DtoMapper<ProductRequestDto, Product, ProductResponseDto> dtoMapper;

    @PostMapping
    ProductResponseDto create(@RequestBody ProductRequestDto requestDto) {
        return dtoMapper.mapToDto(
                productService.save(
                dtoMapper.mapToModel(requestDto)));
    }

    @GetMapping("/{id}")
    ProductResponseDto get(@PathVariable Long id) {
        return dtoMapper.mapToDto(
                productService.get(id));
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id) {
        productService.delete(id);
    }

    @PutMapping("/{id}")
    ProductResponseDto update(@PathVariable Long id,
                              @RequestBody ProductRequestDto requestDto) {
        Product product = dtoMapper.mapToModel(requestDto);
        product.setId(id);
        return dtoMapper.mapToDto(
                productService.save(product));
    }

    @GetMapping("/price-between")
    List<ProductResponseDto> findAllByPriceBetween(@RequestParam BigDecimal from,
                                                   @RequestParam BigDecimal to) {
        return productService.findAllByPriceBetween(from, to)
                .stream()
                .map(dtoMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/in-categories")
    List<ProductResponseDto> findAllByCategories(@RequestParam(value = "categories")
                                                 List<Category> categories) {
        return productService.findAllByCategories(categories)
                .stream()
                .map(dtoMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
