package com.milankas.training.Mappers;

import com.milankas.training.dtos.input.PatchProductInputDTO;
import com.milankas.training.dtos.input.PostProductInputDTO;
import com.milankas.training.dtos.output.ProductOutputDTO;
import com.milankas.training.persistance.entities.ProductEntity;

public class ProductMapper implements ProductMapperInterface {

    @Override
    public ProductOutputDTO EntityToDto(ProductEntity productEntity) {
        return ProductMapperInterface.MAPPER.EntityToDto(productEntity);
    }

    @Override
    public ProductEntity PostDtoToEntity(PostProductInputDTO productDTO) {
        return ProductMapperInterface.MAPPER.PostDtoToEntity(productDTO);
    }

    @Override
    public ProductEntity PatchDtoToEntity(ProductEntity productEntity, PatchProductInputDTO productDTO) {
        if (productDTO.getName() != null) productEntity.setName(productDTO.getName());
        if (productDTO.getDescription() != null) productEntity.setDescription(productDTO.getDescription());
        if (productDTO.getCompanyId() != null) productEntity.setCompanyId(productDTO.getCompanyId());
        if (productDTO.getBlocked() != null) {
            if (productDTO.getBlocked().matches("true|1"))productEntity.setBlocked(true);
            if (productDTO.getBlocked().matches("false|0"))productEntity.setBlocked(false);
        };
        if (productDTO.getCategories() != null) {
            StringBuilder categoriesForEntity = new StringBuilder();
            for(String category: productDTO.getCategories()) {
                categoriesForEntity.append(category).append(";");
            }
            productEntity.setCategories(categoriesForEntity.toString());
        };
        return ProductMapperInterface.MAPPER.PatchDtoToEntity(productEntity,productDTO);
    }

}
