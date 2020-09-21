package com.milankas.training.dtos.order;

import com.milankas.training.dtos.address.PatchAddressInputDTO;
import com.milankas.training.dtos.lineItem.PatchLineItemInputDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class PatchOrderInputDTO {

    private UUID id;

    private UUID userId;

    private List<PatchLineItemInputDTO> lineItems;

    private String emailAddress;

    private PatchAddressInputDTO shopAddress;

}
