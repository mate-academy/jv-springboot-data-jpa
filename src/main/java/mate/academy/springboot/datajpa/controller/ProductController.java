package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.model.dto.request.ProductRequestDto;
import mate.academy.springboot.datajpa.model.dto.responce.ProductResponseDto;
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

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final ProductMapper productMapper;

    public ProductController(ProductService productService,
                             ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @PutMapping("/create")
    public ProductResponseDto create(@RequestBody @Valid ProductRequestDto dto) {
        Product product = productService.save(productMapper.mapToModel(dto));
        return productMapper.mapToDto(product);
    }

    @GetMapping("/{id}")
    public ProductResponseDto getById(@PathVariable Long id) {
        Optional<Product> product = productService.getById(id);
        if (product.isPresent()) {
            return productMapper.mapToDto(product.get());
        }
        throw new RuntimeException("Can`t get product with this id: " + id);
    }

    @DeleteMapping("/{id}")
    public ProductResponseDto delete(@PathVariable Long id) {
        Optional<Product> product = productService.getById(id);
        product.ifPresent(productService::delete);
        throw new RuntimeException("Can`t delete product with this id: " + id);
    }

    @PostMapping("/update/{id}")
    public ProductResponseDto update(@PathVariable Long id,
                                     @RequestBody ProductRequestDto dto) {
        if (productService.getById(id).isPresent()) {
            Product product = productMapper.mapToModel(dto);
            product.setId(id);
            return productMapper.mapToDto(productService.save(product));
        }
        throw new RuntimeException("Can`t update product with this id: " + id);
    }

    @GetMapping("/find/between/price")
    public List<Product> findProductByPriceIsBetween(@RequestParam @NotNull BigDecimal from,
                                                     @RequestParam @NotNull BigDecimal to) {
        return productService.findProductByPriceIsBetween(from, to);
    }

    @GetMapping("/params")
    public List<ProductResponseDto> findAllByParams(@RequestParam Map<String, String> params) {
        return productService.findAllByParams(params).stream()
                .map(productMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
