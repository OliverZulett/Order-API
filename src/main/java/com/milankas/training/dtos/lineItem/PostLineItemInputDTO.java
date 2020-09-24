package com.milankas.training.dtos.lineItem;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import java.util.UUID;

@Getter
@Setter
public class PostLineItemInputDTO {

    private UUID id;

    @Min(value = 1, message = "Its necessary at least 1 line item")
    private Integer qty;

}
