package com.milankas.training.Mappers.order;

import com.milankas.training.dtos.order.PostOrderInputDTO;
import com.milankas.training.dtos.order.OrderOutputDTO;
import com.milankas.training.persistance.entities.OrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface OrderMapperInterface {

    OrderMapperInterface MAPPER = Mappers.getMapper( OrderMapperInterface.class );

    OrderOutputDTO EntityToDto(OrderEntity orderEntity);

    OrderEntity PostDtoToEntity(PostOrderInputDTO orderDTO);

}
