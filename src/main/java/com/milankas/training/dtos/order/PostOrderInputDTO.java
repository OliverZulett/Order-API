package com.milankas.training.dtos.order;

import com.milankas.training.dtos.address.PostAddressInputDTO;
import com.milankas.training.dtos.lineItem.PostLineItemInputDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class PostOrderInputDTO {

    private UUID id;

    private UUID userId;

    private List<PostLineItemInputDTO> lineItems;

    private String emailAddress;

    private PostAddressInputDTO shopAddress;

}
