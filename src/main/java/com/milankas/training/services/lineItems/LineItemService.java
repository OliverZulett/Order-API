package com.milankas.training.services.lineItems;

import com.milankas.training.dtos.lineItem.PatchLineItemInputDTO;
import com.milankas.training.dtos.lineItem.PostLineItemInputDTO;
import com.milankas.training.dtos.lineItem.LineItemOutputDTO;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class LineItemService implements LineItemServiceInterface {

    @Override
    public LineItemOutputDTO findLineItemById(UUID id) {
        return null;
    }

    @Override
    public LineItemOutputDTO saveLineItem(PostLineItemInputDTO lineItem) {
        return null;
    }

    @Override
    public LineItemOutputDTO updateLineItemById(UUID id, PatchLineItemInputDTO lineItemForUpdate) {
        return null;
    }

    @Override
    public Boolean deleteLineItemById(UUID id) {
        return null;
    }

}
