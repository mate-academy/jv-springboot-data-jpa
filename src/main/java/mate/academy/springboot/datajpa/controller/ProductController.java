package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import mate.academy.springboot.datajpa.dto.mapper.ProductMapper;
import mate.academy.springboot.datajpa.dto.request.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.response.ProductResponseDto;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.service.CategoryService;
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
    private final CategoryService categoryService;
    private final ProductMapper productMapper;

    public ProductController(ProductService productService, CategoryService categoryService,
                             ProductMapper productMapper) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.productMapper = productMapper;
    }

    @GetMapping("/inject")
    public String inject() {
        Product iphone1 = new Product();
        iphone1.setTitle("iPhone 1");
        iphone1.setPrice(BigDecimal.valueOf(100));
        iphone1.setCategory(categoryService.get(1L));
        productService.save(iphone1);

        Product iphone2 = new Product();
        iphone2.setTitle("iPhone 2");
        iphone2.setPrice(BigDecimal.valueOf(200));
        iphone2.setCategory(categoryService.get(1L));
        productService.save(iphone2);

        Product iphone3 = new Product();
        iphone3.setTitle("iPhone 3");
        iphone3.setPrice(BigDecimal.valueOf(300));
        iphone3.setCategory(categoryService.get(2L));
        productService.save(iphone3);

        Product iphone4 = new Product();
        iphone4.setTitle("iPhone 4");
        iphone4.setPrice(BigDecimal.valueOf(400));
        iphone4.setCategory(categoryService.get(2L));
        productService.save(iphone4);

        return "Done";
    }

    @PostMapping
    public ProductResponseDto create(@RequestBody ProductRequestDto productRequestDto) {
        Product product = productService.save(productMapper.mapToModel(productRequestDto));
        return productMapper.mapToResponseDto(product);
    }

    @GetMapping("/{id}")
    public ProductResponseDto getById(@PathVariable Long id) {
        return productMapper.mapToResponseDto(productService.get(id));
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }

    @PutMapping("/{id}")
    public ProductResponseDto update(@PathVariable Long id,
                                     @RequestBody ProductRequestDto productRequestDto) {
        Product updatedProduct = productMapper.mapToModel(productRequestDto);
        updatedProduct.setId(id);
        productService.update(updatedProduct);
        return productMapper.mapToResponseDto(updatedProduct);
    }

    @GetMapping("/get-by-price")
    public List<ProductResponseDto> getAllPriceBetween(@RequestParam BigDecimal from,
                                                       @RequestParam BigDecimal to) {
        return productService.getAllBetweenPrice(from, to).stream()
                .map(productMapper::mapToResponseDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/by-categories")
    public List<ProductResponseDto> getByCategories(@RequestParam("category")
                                                        List<String> categories) {
        List<Product> allByCategoryIn = productService.findAllByCategoryIn(categories);
        return allByCategoryIn.stream()
                .map(productMapper::mapToResponseDto)
                .collect(Collectors.toList());

//        List<Product> allByCategoryIn = productService.findAllByCategoryIn(categories);
//        List<ProductResponseDto> productResponseDtos = new ArrayList<>();
//        allByCategoryIn.stream()
//                .forEach((p) -> productResponseDtos.add(productMapper.mapToResponseDto(p)));
//        return productResponseDtos;
    }
}
