package mate.academy.springboot.datajpa.dto;

import lombok.Data;

@Data
public class RequestProduct {
    private String title;
    private Long price;
    private Long categoryId;
}
