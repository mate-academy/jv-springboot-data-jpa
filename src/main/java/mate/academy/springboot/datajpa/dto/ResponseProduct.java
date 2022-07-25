package mate.academy.springboot.datajpa.dto;

import lombok.Data;

@Data
public class ResponseProduct {
    private Long id;
    private String title;
    private Long price;
    private String category;
}
