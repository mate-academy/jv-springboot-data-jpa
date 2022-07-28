package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import mate.academy.springboot.datajpa.model.dto.ProductRequestDto;
import mate.academy.springboot.datajpa.model.dto.ProductResponceDto;
import mate.academy.springboot.datajpa.service.ProductService;
import mate.academy.springboot.datajpa.service.mapper.ProductMapperDto;
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
    private final ProductMapperDto productMapperDto;

    public ProductController(ProductService productService, ProductMapperDto productMapperDto) {
        this.productService = productService;
        this.productMapperDto = productMapperDto;
    }

    @GetMapping("/id")
    public ProductResponceDto getById(@PathVariable Long id) {
        return productMapperDto.mapToDto(productService.getProductById(id));
    }

    @GetMapping("/price")
    public List<ProductResponceDto> getAllByPriceBetween(@RequestParam BigDecimal priceFrom,
                                                         @RequestParam BigDecimal priceTo) {
        return productService.findAllByPriceBetween(priceFrom, priceTo).stream()
                .map(productMapperDto::mapToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/categories")
    public List<ProductResponceDto> getAllProductsInCategories(@RequestParam
                                                               List<Long> categoriesId) {
        return productService.findAllByCategoryIdIn(categoriesId).stream()
                .map(productMapperDto::mapToDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    public ProductResponceDto create(@RequestBody ProductRequestDto productRequestDto) {
        return productMapperDto
                .mapToDto(productService.save(productMapperDto.mapToModel(productRequestDto)));
    }

    @PutMapping("/id")
    public ProductResponceDto update(@PathVariable Long is,
                                     @RequestBody ProductRequestDto productRequestDto) {
        return productMapperDto
                .mapToDto(productService.update(productMapperDto.mapToModel(productRequestDto)));
    }

    @DeleteMapping("/id")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }
}
