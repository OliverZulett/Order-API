package com.milankas.training.services.address;

import com.milankas.training.dtos.address.PatchAddressInputDTO;
import com.milankas.training.dtos.address.PostAddressInputDTO;
import com.milankas.training.dtos.address.AddressOutputDTO;

import java.util.UUID;

public interface AddressServiceInterface {

    AddressOutputDTO findAddressById(UUID id);

    AddressOutputDTO saveAddress(PostAddressInputDTO address);

    AddressOutputDTO updateAddressById(UUID id, PatchAddressInputDTO addressForUpdate);

    Boolean deleteProductById(UUID id);

}
