package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.springboot.datajpa.dto.category.CategoryRequestDto;
import mate.academy.springboot.datajpa.dto.product.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.product.ProductResponseDto;
import mate.academy.springboot.datajpa.service.CategoryService;
import mate.academy.springboot.datajpa.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;

    @GetMapping
    public List<ProductResponseDto> getAllProducts() {
        return productService.getAll();
    }

    @GetMapping("/price")
    public List<ProductResponseDto> getProductsByPrice(
            @RequestParam BigDecimal from,
            @RequestParam BigDecimal to) {
        return productService.findByPriceBetween(from, to);
    }

    @GetMapping("/{id}")
    public ProductResponseDto getProductsById(@PathVariable Long id) {
        return productService.get(id);
    }

    @PostMapping
    public ProductResponseDto create(@RequestBody ProductRequestDto dto) {
        return productService.save(dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable Long id) {
        productService.remove(id);
    }

    @GetMapping("/inject")
     public void saveProducts() {
        CategoryRequestDto gross = new CategoryRequestDto();
        gross.setName("GROSS");
        categoryService.save(gross);

        CategoryRequestDto fruit = new CategoryRequestDto();
        fruit.setName("FRUIT");
        categoryService.save(fruit);

        CategoryRequestDto veg = new CategoryRequestDto();
        veg.setName("VEGETABLES");
        categoryService.save(veg);

        ProductRequestDto dto = new ProductRequestDto();
        dto.setTitle("buckwheat");
        dto.setPrice(BigDecimal.valueOf(32.30));
        dto.setCategoryId(1L);
        productService.save(dto);

        ProductRequestDto dto2 = new ProductRequestDto();
        dto2.setTitle("peach");
        dto2.setPrice(BigDecimal.valueOf(19.50));
        dto2.setCategoryId(2L);
        productService.save(dto2);

        ProductRequestDto dto3 = new ProductRequestDto();
        dto3.setTitle("tomato");
        dto3.setPrice(BigDecimal.valueOf(12.20));
        dto3.setCategoryId(3L);
        productService.save(dto3);

        ProductRequestDto dto4 = new ProductRequestDto();
        dto4.setTitle("potato");
        dto4.setPrice(BigDecimal.valueOf(8.10));
        dto4.setCategoryId(3L);
        productService.save(dto4);
    }
}
