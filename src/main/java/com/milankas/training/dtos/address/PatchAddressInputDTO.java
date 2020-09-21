package com.milankas.training.dtos.address;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class PatchAddressInputDTO {

    private UUID id;

    private String addressLine1;

    private String addressLine2;

    private String contactName;

    private String contactPhone;

    private String state;

    private String city;

    private String zipCode;

    private String countryCode;

}
