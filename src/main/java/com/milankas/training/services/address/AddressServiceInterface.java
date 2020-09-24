package com.milankas.training.services.address;

import com.milankas.training.dtos.address.PostAddressInputDTO;
import com.milankas.training.persistance.entities.AddressEntity;
import org.springframework.stereotype.Service;

public interface AddressServiceInterface {

    AddressEntity validateAddressToSave(PostAddressInputDTO address);

}
