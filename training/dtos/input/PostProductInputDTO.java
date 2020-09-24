package com.milankas.training.dtos.input;

import com.milankas.training.validators.BooleanValidator.BooleanConstraint;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class PostProductInputDTO {

    private UUID id;

    @NotNull(message = "Name is required")
    @Size(min = 5, max = 100, message = "Name must be between 5 and 100 characters")
    private String name;

    @NotNull(message = "Description is required")
    @Size(min = 5, max = 250, message = "Description must be between 5 and 250 characters")
    private String description;

    @NotNull(message = "CompanyId is required")
    private UUID companyId;

    @NotNull(message = "Blocked is required")
    @BooleanConstraint(message = "Blocked allowed input: true, false, 1 or 0")
    private String blocked;

    @NotNull(message = "Categories are required")
    @Size(min=1, max=10, message = "Product must have at least one category")
    private List<String> categories;

}
