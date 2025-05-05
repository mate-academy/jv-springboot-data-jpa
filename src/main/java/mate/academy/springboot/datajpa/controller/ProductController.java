package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import mate.academy.springboot.datajpa.dto.request.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.response.ProductResponseDto;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.service.ProductService;
import mate.academy.springboot.datajpa.service.mapper.ProductMapper;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final ProductMapper productMapper;

    @GetMapping("/{id}")
    public ProductResponseDto getProductById(@PathVariable Long id) {
        return productMapper.mapToDto(productService.get(id));
    }

    @PostMapping
    public ProductResponseDto createProduct(@RequestBody ProductRequestDto requestDto) {
        Product product = productService.add(productMapper.mapToModel(requestDto));
        return productMapper.mapToDto(product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.delete(id);
    }

    @PutMapping("/{id}")
    public ProductResponseDto updateProduct(@RequestBody ProductRequestDto requestDto,
                                            @PathVariable Long id) {
        Product product = productMapper.mapToModel(requestDto);
        product.setId(id);
        Product updatedProduct = productService.update(product);
        return productMapper.mapToDto(updatedProduct);
    }

    @GetMapping("/by-price")
    public List<ProductResponseDto> getAllProductsPriceBetween(@RequestParam BigDecimal from,
                                                               @RequestParam BigDecimal to) {
        return productService.getAllByPriceBetween(from, to)
                .stream()
                .map(productMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/by-category")
    public List<ProductResponseDto> getAllProductsByCategories(
            @RequestParam Map<String, String> params) {
        return productService.getAllByCategory(params)
                .stream()
                .map(productMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
