package mate.academy.springboot.datajpa.dto.request.mappper.impl;

import lombok.AllArgsConstructor;
import mate.academy.springboot.datajpa.dto.request.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.request.mappper.RequestMapper;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.service.CategoryService;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ProductRequestMapper implements RequestMapper<Product, ProductRequestDto> {
    private CategoryService categoryService;

    @Override
    public Product mapToModel(ProductRequestDto dto) {
        Product product = new Product();
        product.setPrice(dto.getPrice());
        product.setTitle(dto.getTitle());
        product.setCategory(categoryService.findById(dto.getCategoryId()));
        return product;
    }
}
