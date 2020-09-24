package com.milankas.training.dtos.input;

import com.milankas.training.validators.BooleanValidator.BooleanConstraint;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class PatchProductInputDTO {

    private UUID id;

    @Size(min = 5, max = 200, message = "Name must be between 5 and 200 characters")
    private String name;

    @Size(min = 5, max = 250, message = "Description must be between 10 and 250 characters")
    private String description;
    private UUID companyId;

    @BooleanConstraint(message = "Blocked allowed input: true, false, 1 or 0")
    private String blocked;

    @Size(min=1, max=10, message = "Product must have at least one category")
    private List<String> categories;

}
