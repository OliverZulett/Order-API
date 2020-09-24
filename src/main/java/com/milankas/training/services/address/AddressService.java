package com.milankas.training.services.address;

import com.milankas.training.Mappers.address.AddressMapperInterface;
import com.milankas.training.dtos.address.PostAddressInputDTO;
import com.milankas.training.persistance.entities.AddressEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Service
public class AddressService implements AddressServiceInterface {

    @Autowired
    AddressMapperInterface addressMapper;

    @Override
    public AddressEntity validateAddressToSave(@Valid PostAddressInputDTO address) {
        return addressMapper.PostDtoToEntity(address);
    }

}
