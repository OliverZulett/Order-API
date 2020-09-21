package com.milankas.training.services.lineItems;

import com.milankas.training.dtos.lineItem.PatchLineItemInputDTO;
import com.milankas.training.dtos.lineItem.PostLineItemInputDTO;
import com.milankas.training.dtos.lineItem.LineItemOutputDTO;

import java.util.UUID;

public interface LineItemServiceInterface {

    LineItemOutputDTO findLineItemById(UUID id);

    LineItemOutputDTO saveLineItem(PostLineItemInputDTO lineItem);

    LineItemOutputDTO updateLineItemById(UUID id, PatchLineItemInputDTO lineItemForUpdate);

    Boolean deleteLineItemById(UUID id);

}
