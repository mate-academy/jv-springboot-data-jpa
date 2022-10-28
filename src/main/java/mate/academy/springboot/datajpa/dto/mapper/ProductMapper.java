package mate.academy.springboot.datajpa.dto.mapper;

import mate.academy.springboot.datajpa.dto.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.ProductResponseDto;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.service.CategoryService;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public ProductResponseDto toResponseDto(Product product) {
         ProductResponseDto responseDto = new ProductResponseDto();
         responseDto.setId(product.getId());
         responseDto.setTitle(product.getTitle());
         responseDto.setPrice(product.getPrice());
         responseDto.setCategoryId(product.getCategory().getId());
         return responseDto;
    }

    public Product toModel(ProductRequestDto requestDto){
        Product product = new Product();
        product.setPrice(requestDto.getPrice());
        product.setTitle(requestDto.getTitle());
        product.setCategory(CategoryService. requestDto.getCategoryId());
        return
    }
}
