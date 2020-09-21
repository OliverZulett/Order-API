package com.milankas.training.Mappers.address;

import com.milankas.training.dtos.address.PostAddressInputDTO;
import com.milankas.training.dtos.address.AddressOutputDTO;
import com.milankas.training.persistance.entities.AddressEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AddressMapperInterface {

    AddressMapperInterface MAPPER = Mappers.getMapper( AddressMapperInterface.class );

    AddressOutputDTO EntityToDto(AddressEntity addressEntity);

    AddressEntity PostDtoToEntity(PostAddressInputDTO addressDTO);

}
