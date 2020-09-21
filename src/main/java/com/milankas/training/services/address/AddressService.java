package com.milankas.training.services.address;

import com.milankas.training.dtos.address.PatchAddressInputDTO;
import com.milankas.training.dtos.address.PostAddressInputDTO;
import com.milankas.training.dtos.address.AddressOutputDTO;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AddressService implements AddressServiceInterface{

    @Override
    public AddressOutputDTO findAddressById(UUID id) {
        return null;
    }

    @Override
    public AddressOutputDTO saveAddress(PostAddressInputDTO address) {
        return null;
    }

    @Override
    public AddressOutputDTO updateAddressById(UUID id, PatchAddressInputDTO addressForUpdate) {
        return null;
    }

    @Override
    public Boolean deleteProductById(UUID id) {
        return null;
    }

}
