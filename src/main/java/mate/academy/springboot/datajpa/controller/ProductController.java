package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.model.dto.request.ProductRequestDto;
import mate.academy.springboot.datajpa.model.dto.response.ProductResponseDto;
import mate.academy.springboot.datajpa.service.ProductService;
import mate.academy.springboot.datajpa.service.mapper.RequestDtoMapper;
import mate.academy.springboot.datajpa.service.mapper.ResponseDtoMapper;
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
    private final RequestDtoMapper<ProductRequestDto, Product> productRequestDtoMapper;
    private final ResponseDtoMapper<ProductResponseDto, Product> productResponseDtoMapper;

    @PostMapping
    public ProductResponseDto save(@RequestBody ProductRequestDto requestDto) {
        Product product = productService.save(productRequestDtoMapper.mapToModel(requestDto));
        return productResponseDtoMapper.mapToDto(product);
    }

    @GetMapping("/{id}")
    public ProductResponseDto get(@PathVariable Long id) {
        return productResponseDtoMapper.mapToDto(productService.get(id));
    }

    @PutMapping("/{id}")
    public ProductResponseDto update(@PathVariable Long id,
                                          @RequestBody ProductRequestDto requestDto) {
        Product product = productRequestDtoMapper.mapToModel(requestDto);
        product.setId(id);
        productService.update(product);
        return productResponseDtoMapper.mapToDto(product);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }

    @GetMapping("/by-price")
    public List<ProductResponseDto> findAllByPriceBetween(@RequestParam BigDecimal from,
                                                          @RequestParam BigDecimal to) {
        List<Product> productList = productService.findAllByPriceBetween(from, to);
        return productList
                .stream()
                .map(productResponseDtoMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/by-categories")
    public List<ProductResponseDto> findAllByCategoryIdIn(@RequestParam List<Long> categories) {
        List<Product> productList = productService.findAllByCategoryIdIn(categories);
        return productList
                .stream()
                .map(productResponseDtoMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
