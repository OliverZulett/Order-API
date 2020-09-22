package com.milankas.training.Mappers.address;

import com.milankas.training.dtos.address.PatchAddressInputDTO;
import com.milankas.training.dtos.address.PostAddressInputDTO;
import com.milankas.training.dtos.address.AddressOutputDTO;
import com.milankas.training.persistance.entities.AddressEntity;

public class AddressMapper implements AddressMapperInterface {

    @Override
    public AddressOutputDTO EntityToDto(AddressEntity addressEntity) {
        return AddressMapperInterface.MAPPER.EntityToDto(addressEntity);
    }

    @Override
    public PatchAddressInputDTO EntityToPatchDto(AddressEntity addressEntity) {
        return AddressMapperInterface.MAPPER.EntityToPatchDto(addressEntity);
    }

    @Override
    public AddressEntity PostDtoToEntity(PostAddressInputDTO addressDTO) {
        return AddressMapperInterface.MAPPER.PostDtoToEntity(addressDTO);
    }

    @Override
    public AddressEntity PatchDtoToEntity(PatchAddressInputDTO addressDTO) {
        return AddressMapperInterface.MAPPER.PatchDtoToEntity(addressDTO);
    }

}
