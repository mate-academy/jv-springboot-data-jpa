package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import mate.academy.springboot.datajpa.mapper.impl.request.ProductDtoRequestMapper;
import mate.academy.springboot.datajpa.mapper.impl.response.ProductDtoResponseMapper;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.model.dto.request.ProductRequestDto;
import mate.academy.springboot.datajpa.model.dto.response.ProductResponseDto;
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
    private final ProductDtoRequestMapper productDtoRequestMapper;
    private final ProductDtoResponseMapper productDtoResponseMapper;

    public ProductController(ProductService productService,
                             ProductDtoRequestMapper productDtoRequestMapper,
                             ProductDtoResponseMapper productDtoResponseMapper) {
        this.productService = productService;
        this.productDtoRequestMapper = productDtoRequestMapper;
        this.productDtoResponseMapper = productDtoResponseMapper;
    }

    @PostMapping()
    public ProductResponseDto add(@RequestBody ProductRequestDto dto) {
        Product product = productService.save(productDtoRequestMapper.fromDto(dto));
        return productDtoResponseMapper.toDto(product);
    }

    @GetMapping()
    public ProductResponseDto get(@RequestParam Long id) {
        return productDtoResponseMapper.toDto(productService.get(id));
    }

    @PutMapping("/{id}")
    public ProductResponseDto update(@PathVariable Long id, @RequestBody ProductRequestDto dto) {
        Product product = productDtoRequestMapper.fromDto(dto);
        product.setId(id);
        return productDtoResponseMapper.toDto(productService.update(product));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }

    @GetMapping("/price-between")
    public List<ProductResponseDto> findAllByPriceBetween(
            @RequestParam BigDecimal priceFrom, BigDecimal priceTo) {
        return productService.findAllByPriceBetween(priceFrom, priceTo).stream()
                .map(productDtoResponseMapper::toDto)
                .collect(Collectors.toList());
    }
    
    @GetMapping("/filter")
    public List<ProductResponseDto> findAll(@RequestParam Map<String, String> params) {
        return productService.findAllByFilter(params).stream()
                .map(productDtoResponseMapper::toDto)
                .collect(Collectors.toList());
    }
}
