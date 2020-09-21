package com.milankas.training.dtos.lineItem;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class PostLineItemInputDTO {

    private UUID id;

    private String qty;

}
