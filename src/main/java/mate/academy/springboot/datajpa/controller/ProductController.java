package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import mate.academy.springboot.datajpa.dto.request.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.response.ProductResponseDto;
import mate.academy.springboot.datajpa.model.Product;
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
public class ProductController {
    private final ProductService productService;
    private final RequestDtoMapper<ProductRequestDto, Product> requestDtoMapper;
    private final ResponseDtoMapper<ProductResponseDto, Product> responseDtoMapper;

    public ProductController(ProductService productService,
                             RequestDtoMapper<ProductRequestDto, Product> requestDtoMapper,
                             ResponseDtoMapper<ProductResponseDto, Product> responseDtoMapper) {
        this.productService = productService;
        this.requestDtoMapper = requestDtoMapper;
        this.responseDtoMapper = responseDtoMapper;
    }

    @PostMapping
    public ProductResponseDto addProduct(@RequestBody ProductRequestDto requestDto) {
        Product product = productService.save(requestDtoMapper.mapToModel(requestDto));
        return responseDtoMapper.mapToDto(product);
    }

    @GetMapping("/{id}")
    public ProductResponseDto getProduct(@PathVariable Long id) {
        return responseDtoMapper.mapToDto(productService.get(id));
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id) {
        productService.delete(id);
    }

    @PutMapping("/{id}")
    public ProductResponseDto update(@PathVariable Long id,
                                     @RequestBody ProductRequestDto requestDto) {
        Product product = requestDtoMapper.mapToModel(requestDto);
        product.setId(id);
        return responseDtoMapper.mapToDto(productService.save(product));
    }

    @GetMapping("/price")
    public List<ProductResponseDto> getAllProductsByPriceBetween(@RequestParam BigDecimal fromPrice,
                                                          @RequestParam BigDecimal toPrice) {
        return productService.getAllByThePriceBetween(fromPrice, toPrice).stream()
                .map(responseDtoMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/categories")
    public List<ProductResponseDto> getAllProductsByCategoriesIn(@RequestParam Map<String,
            String> params) {
        return productService.getAllByTheCategoriesIn(params).stream()
                .map(responseDtoMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
