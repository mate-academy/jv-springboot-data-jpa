package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.model.dto.request.ProductRequestDto;
import mate.academy.springboot.datajpa.model.dto.response.ProductResponseDto;
import mate.academy.springboot.datajpa.service.ProductService;
import mate.academy.springboot.datajpa.service.mapper.ProductMapper;
import org.springframework.transaction.annotation.Transactional;
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

    public ProductController(ProductService productService, ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @PostMapping
    public ProductResponseDto add(@RequestBody ProductRequestDto requestDto) {
        Product product = productService.add(productMapper.dtoToModel(requestDto));
        return productMapper.modelToDto(product);
    }

    @GetMapping("/{id}")
    @Transactional
    public ProductResponseDto getById(@PathVariable Long id) {
        return productMapper.modelToDto(productService.getById(id));
    }

    @GetMapping("/price-range")
    @Transactional
    public List<ProductResponseDto> getAllBetweenPrice(@RequestParam BigDecimal priceFrom,
                                                       @RequestParam BigDecimal priceTo) {
        return productService.getAllBetweenPrice(priceFrom, priceTo).stream()
                .map(productMapper::modelToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/categories")
    @Transactional
    public List<ProductResponseDto> getAllByCategories(@RequestParam List<Long> ids) {
        return productService.getAllByCategories(ids).stream()
                .map(productMapper::modelToDto)
                .collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.deleteById(id);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody ProductRequestDto requestDto) {
        Product product = productMapper.dtoToModel(requestDto);
        product.setId(id);
        productService.update(product);
    }

    @GetMapping("/inject")
    public String inject() {
        Product tv = new Product();
        tv.setTitle("LG");
        tv.setCategory(new Category("TV"));
        tv.setPrice(BigDecimal.valueOf(1111.11));

        Product phone = new Product();
        phone.setTitle("iPhone");
        phone.setCategory(new Category("Phone"));
        phone.setPrice(BigDecimal.valueOf(999.99));

        Product ps = new Product();
        ps.setTitle("PS 5");
        ps.setCategory(new Category("Game console"));
        ps.setPrice(BigDecimal.valueOf(555.55));

        productService.add(tv);
        productService.add(phone);
        productService.add(ps);

        return "Data injected " + LocalTime.now();
    }
}
