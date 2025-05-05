package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.model.dto.request.RequestProductDto;
import mate.academy.springboot.datajpa.model.dto.response.ResponseProductDto;
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

@RequiredArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final DtoMapper<Product, RequestProductDto, ResponseProductDto> productMapper;

    @PostMapping
    public ResponseProductDto create(@RequestBody RequestProductDto productDto) {
        Product product = productMapper.toModel(productDto);
        return productMapper.toDto(productService.create(product));
    }

    @GetMapping("/{id}")
    public ResponseProductDto get(@PathVariable Long id) {
        return productMapper.toDto(productService.get(id));
    }

    @PutMapping("/{id}")
    public ResponseProductDto update(@PathVariable Long id,
                                     @RequestBody RequestProductDto productDto
    ) {
        Product product = productMapper.toModel(productDto);
        product.setId(id);
        return productMapper.toDto(productService.update(product));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }

    @GetMapping("/by-price")
    public List<ResponseProductDto> findAllByPrice(@RequestParam BigDecimal from,
                                                   @RequestParam BigDecimal to
    ) {
        List<Product> productsByPrice = productService.findAllByPriceBetween(from, to);
        return convertListToDto(productsByPrice);
    }

    @GetMapping("/by-category")
    public List<ResponseProductDto> findAllByCategory(@RequestParam List<String> category) {
        List<Product> productsByCategories = productService.findAllByCategoryIn(category);
        return convertListToDto(productsByCategories);
    }

    private List<ResponseProductDto> convertListToDto(List<Product> products) {
        return products.stream()
                .map(productMapper::toDto)
                .toList();
    }
}
