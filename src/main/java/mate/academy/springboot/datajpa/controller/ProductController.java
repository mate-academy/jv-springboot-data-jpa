package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import mate.academy.springboot.datajpa.dto.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.ProductResponseDto;
import mate.academy.springboot.datajpa.dto.mapper.ProductMapper;
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

@RestController()
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final ProductMapper mapper;

    public ProductController(
            ProductService productService,
            ProductMapper mapper
    ) {
        this.productService = productService;
        this.mapper = mapper;
    }

    @PostMapping
    public ProductResponseDto create(@RequestBody ProductRequestDto receiveDto) {
        return mapper.toDto(productService.createOrUpdate(mapper.toModel(receiveDto)));
    }

    @GetMapping("/{id}")
    public ProductResponseDto getById(@PathVariable Long id) {
        return mapper.toDto(productService.getById(id));
    }

    @PutMapping("/{id}")
    public ProductResponseDto update(
            @PathVariable Long id,
            @RequestBody ProductRequestDto receiveDto
    ) {
        Product product = mapper.toModel(receiveDto);
        product.setId(id);
        return mapper.toDto(productService.createOrUpdate(product));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }

    @GetMapping("/byPrice")
    public List<ProductResponseDto> findByPriceBetween(
            @RequestParam BigDecimal priceFrom,
            @RequestParam BigDecimal priceTo
    ) {
        return productService.findAllBetweenPrice(priceFrom, priceTo)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping
    public List<ProductResponseDto> findAll(@RequestParam Map<String, String> params) {
        return productService.findAll(params)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
}
