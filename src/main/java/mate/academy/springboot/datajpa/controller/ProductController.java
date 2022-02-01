package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import mate.academy.springboot.datajpa.dto.request.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.request.mappper.RequestMapper;
import mate.academy.springboot.datajpa.dto.response.ProductResponseDto;
import mate.academy.springboot.datajpa.dto.response.mapper.ResponseMapper;
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
    private ProductService productService;
    private RequestMapper<Product, ProductRequestDto> requestMapper;
    private ResponseMapper<ProductResponseDto, Product> responseMapper;

    @GetMapping("/{id}")
    public ProductResponseDto findById(@PathVariable Long id) {
        return responseMapper.mapToDto(productService.findById(id));
    }

    @GetMapping("/price-between")
    public List<ProductResponseDto> findBetweenPrices(@RequestParam BigDecimal from,
                                                      @RequestParam BigDecimal to) {
        return productService.findAllBetweenPrice(from, to)
                .stream()
                .map(responseMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/in-category")
    public List<ProductResponseDto> findInCategory(@RequestParam List<String> categories) {
        return productService.findInCategories(categories)
                .stream()
                .map(responseMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    public ProductResponseDto save(@RequestBody ProductRequestDto requestDto) {
        return responseMapper.mapToDto(productService.save(requestMapper.mapToModel(requestDto)));
    }

    @PutMapping
    public ProductResponseDto update(@RequestParam Long productId,
                                     @RequestBody ProductRequestDto requestDto) {
        Product update = productService.update(productId, requestMapper.mapToModel(requestDto));
        return responseMapper.mapToDto(update);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }
}
