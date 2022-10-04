package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import mate.academy.springboot.datajpa.dto.request.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.response.ProductResponseDto;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.service.ProductService;
import mate.academy.springboot.datajpa.service.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductMapper productMapper;

    @PostMapping
    public ResponseEntity<ProductResponseDto> create(
            @RequestBody @Valid ProductRequestDto requestDto) {
        Product product = productService.save(productMapper.mapToModel(requestDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(productMapper.mapToDto(product));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> getById(@PathVariable Long id) {
        Product product = productService.getById(id);
        return ResponseEntity.ok(productMapper.mapToDto(product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDto> update(
            @PathVariable Long id, @RequestBody @Valid ProductRequestDto requestDto) {
        Product product = productMapper.mapToModel(requestDto);
        product.setId(id);
        productService.save(product);
        return ResponseEntity.ok(productMapper.mapToDto(product));
    }

    @GetMapping("/price")
    public ResponseEntity<List<ProductResponseDto>> getAllByPriceBetween(
            @RequestParam BigDecimal from, @RequestParam BigDecimal to) {
        List<Product> products = productService.getAllByPriceRange(from, to);
        List<ProductResponseDto> responseDtoList = products.stream()
                .map(productMapper::mapToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDtoList);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> getAllByCategories(
            @RequestParam("category") List<String> categories) {
        List<Product> products = productService.getAllByCategories(categories);
        return ResponseEntity.ok(products.stream()
                .map(productMapper::mapToDto)
                .collect(Collectors.toList()));
    }
}
