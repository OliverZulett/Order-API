package com.milankas.training.dtos.output;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class ProductOutputDTO {

    private UUID id;
    private String name;
    private String description;
    private UUID companyId;
    private Boolean blocked;
    private List<String> categories;

}
