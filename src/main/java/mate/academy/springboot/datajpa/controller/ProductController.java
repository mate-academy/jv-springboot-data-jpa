package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.springboot.datajpa.dto.request.RequestProductDto;
import mate.academy.springboot.datajpa.dto.response.ResponseProductDto;
import mate.academy.springboot.datajpa.model.Category;
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

@RequiredArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final DtoMapper<Product, RequestProductDto, ResponseProductDto> productMapper;

    @PostMapping
    public ResponseProductDto create(@RequestBody RequestProductDto productDto) {
        Product product = productMapper.toModel(productDto);
        return productMapper.toDto(productService.save(product));
    }

    @GetMapping("/{id}")
    public ResponseProductDto getById(@PathVariable Long id) {
        return productMapper.toDto(productService.getById(id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }

    @PutMapping("/{id}")
    public ResponseProductDto update(@PathVariable Long id,
                                     @RequestBody RequestProductDto productDto) {
        Product product = productMapper.toModel(productDto);
        product.setId(id);
        return productMapper.toDto(productService.update(product));
    }

    @GetMapping("/by-price")
    public List<ResponseProductDto> getAllByPriceBetween(@RequestParam BigDecimal from,
                                                         @RequestParam BigDecimal to) {
        return modelsListToDto(productService.findAllByPriceBetween(from, to));
    }

    @GetMapping("by-category")
    public List<ResponseProductDto> getAllByCategoryIn(List<Category> categories) {
        return modelsListToDto(productService.findAllByCategory(categories));

    }

    @GetMapping
    public List<ResponseProductDto> getAll() {
        return modelsListToDto(productService.findAll());
    }

    private List<ResponseProductDto> modelsListToDto(List<Product> products) {
        return products.stream()
                .map(productMapper::toDto)
                .toList();
    }
}
