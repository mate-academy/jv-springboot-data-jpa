package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import mate.academy.springboot.datajpa.dto.request.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.response.ProductResponseDto;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.service.ProductService;
import mate.academy.springboot.datajpa.service.mapper.DtoMapper;
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
    private final DtoMapper<ProductRequestDto, ProductResponseDto, Product> productDtoMapper;

    public ProductController(ProductService productService,
                             DtoMapper<ProductRequestDto, ProductResponseDto,
                                     Product> productDtoMapper) {
        this.productService = productService;
        this.productDtoMapper = productDtoMapper;
    }

    @PostMapping
    public ProductResponseDto add(@RequestBody ProductRequestDto productRequestDto) {
        Product product =
                productService.add(productDtoMapper.mapToModel(productRequestDto));
        return productDtoMapper.mapToDto(product);
    }

    @GetMapping("/{id}")
    public ProductResponseDto get(@PathVariable Long id) {
        return productDtoMapper.mapToDto(productService.get(id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }

    @PutMapping("/{id}")
    public ProductResponseDto update(@PathVariable Long id,
                                     @RequestBody ProductRequestDto productRequestDto) {
        Product product = productDtoMapper.mapToModel(productRequestDto);
        product.setId(id);
        return productDtoMapper.mapToDto(productService.update(product));
    }

    @GetMapping("/price")
    public List<ProductResponseDto> getAllByPrice(@RequestParam BigDecimal from,
                                                  @RequestParam BigDecimal to) {
        return productService.findAllByPriceBetween(from, to).stream()
                .map(productDtoMapper::mapToDto)
                .toList();
    }

    @GetMapping
    public List<ProductResponseDto> getAll(@RequestParam Map<String, String> params) {
        return productService.getAll(params).stream()
                .map(productDtoMapper::mapToDto)
                .toList();
    }
}
