package com.milankas.training.Mappers.order;

import com.milankas.training.Mappers.address.AddressMapper;
import com.milankas.training.Mappers.address.AddressMapperInterface;
import com.milankas.training.Mappers.lineItem.LineItemMapper;
import com.milankas.training.Mappers.lineItem.LineItemMapperInterface;
import com.milankas.training.dtos.address.AddressOutputDTO;
import com.milankas.training.dtos.address.PatchAddressInputDTO;
import com.milankas.training.dtos.lineItem.LineItemOutputDTO;
import com.milankas.training.dtos.lineItem.PatchLineItemInputDTO;
import com.milankas.training.dtos.lineItem.PostLineItemInputDTO;
import com.milankas.training.dtos.order.PatchOrderInputDTO;
import com.milankas.training.dtos.order.PostOrderInputDTO;
import com.milankas.training.dtos.order.OrderOutputDTO;
import com.milankas.training.persistance.entities.AddressEntity;
import com.milankas.training.persistance.entities.LineItemEntity;
import com.milankas.training.persistance.entities.OrderEntity;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapperInterface {

    OrderMapperInterface MAPPER = Mappers.getMapper( OrderMapperInterface.class );
    AddressMapperInterface addressMapper = new AddressMapper();
    LineItemMapperInterface lineItemMapper = new LineItemMapper();

    @Mappings({
            @Mapping(target = "shopAddress", source = "orderEntity", qualifiedByName = "addressEntityToDTO"),
            @Mapping(target = "lineItems", source = "orderEntity", qualifiedByName = "lineItemEntityToDTO")
    })
    OrderOutputDTO EntityToDto(OrderEntity orderEntity);

    @Mappings({
            @Mapping(target = "shopAddress", source = "orderDTO", qualifiedByName = "addressDTOToEntity"),
            @Mapping(target = "lineItems", source = "orderDTO", qualifiedByName = "lineItemsDTOToEntity")
    })
    OrderEntity PostDtoToEntity(PostOrderInputDTO orderDTO);

    @BeforeMapping
    default void validateFields(@MappingTarget OrderEntity orderEntity, PatchOrderInputDTO orderDTO) {
        orderDTO.setId(orderEntity.getId());
        if (orderDTO.getUserId() == null) orderDTO.setUserId(orderEntity.getUserId());
        if (orderDTO.getEmailAddress() == null) orderDTO.setEmailAddress(orderEntity.getEmailAddress());
        if (orderDTO.getLineItems() == null) orderDTO.setLineItems(lineItemEntityToPatchDTO(orderEntity));
        if (orderDTO.getShopAddress() == null) orderDTO.setShopAddress(addressEntityToPatchDTO(orderEntity));
    }
    @Mappings({
            @Mapping(target = "shopAddress", source = "orderDTO", qualifiedByName = "addressPatchDTOToEntity"),
            @Mapping(target = "lineItems", source = "orderDTO", qualifiedByName = "lineItemsPatchDTOToEntity")
    })
    OrderEntity PatchDtoToEntity(@MappingTarget OrderEntity orderEntity, PatchOrderInputDTO orderDTO);

    @Named("addressDTOToEntity")
    public static AddressEntity addressDTOToEntity(PostOrderInputDTO order) {
        return addressMapper.PostDtoToEntity(order.getShopAddress());
    }

    @Named("lineItemsDTOToEntity")
    public static List<LineItemEntity> lineItemsDTOToEntity(PostOrderInputDTO order) {
        List<LineItemEntity> lineItemEntities = new ArrayList<>();
        order.getLineItems().forEach((PostLineItemInputDTO lineItem) -> {
            lineItemEntities.add(lineItemMapper.PostDtoToEntity(lineItem));
        });
        return lineItemEntities;
    }

    @Named("addressPatchDTOToEntity")
    public static AddressEntity addressPatchDTOToEntity(PatchOrderInputDTO order) {
        return addressMapper.PatchDtoToEntity(order.getShopAddress());
    }

    @Named("lineItemsPatchDTOToEntity")
    public static List<LineItemEntity> lineItemsPatchDTOToEntity(PatchOrderInputDTO order) {
        List<LineItemEntity> lineItemEntities = new ArrayList<>();
        order.getLineItems().forEach((PatchLineItemInputDTO lineItem) -> {
            lineItemEntities.add(lineItemMapper.PatchDtoToEntity(lineItem));
        });
        return lineItemEntities;
    }

    @Named("addressEntityToDTO")
    public static AddressOutputDTO addressEntityToDTO(OrderEntity order) {
        return addressMapper.EntityToDto(order.getShopAddress());
    }

    @Named("lineItemEntityToDTO")
    public static List<LineItemOutputDTO> lineItemEntityToDTO(OrderEntity order) {
        List<LineItemOutputDTO> lineItems = new ArrayList<>();
        order.getLineItems().forEach((LineItemEntity lineItem) -> {
            lineItems.add(lineItemMapper.EntityToDto(lineItem));
        });
        return lineItems;
    }

    @Named("addressEntityToPatchDTO")
    public static PatchAddressInputDTO addressEntityToPatchDTO(OrderEntity order) {
        return addressMapper.EntityToPatchDto(order.getShopAddress());
    }

    @Named("lineItemEntityToPatchDTO")
    public static List<PatchLineItemInputDTO> lineItemEntityToPatchDTO(OrderEntity order) {
        List<PatchLineItemInputDTO> lineItems = new ArrayList<>();
        order.getLineItems().forEach((LineItemEntity lineItem) -> {
             lineItems.add(lineItemMapper.EntityToPatchDto(lineItem));
        });
        return lineItems;
    }

}
