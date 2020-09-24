package com.milankas.training.Mappers;

import com.milankas.training.dtos.input.PatchProductInputDTO;
import com.milankas.training.dtos.input.PostProductInputDTO;
import com.milankas.training.dtos.output.ProductOutputDTO;
import com.milankas.training.persistance.entities.ProductEntity;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.Arrays;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapperInterface {

    ProductMapperInterface MAPPER = Mappers.getMapper( ProductMapperInterface.class );

    @Mapping(source="categories", target = "categories", qualifiedByName = "stringToArray")
    ProductOutputDTO EntityToDto(ProductEntity productEntity);

    @Mapping(source="categories", target = "categories", qualifiedByName = "arrayToString")
    @Mapping(source="blocked", target = "blocked", qualifiedByName = "stringToBoolean")
    ProductEntity PostDtoToEntity(PostProductInputDTO productDTO);

    @BeforeMapping
    default void validateFields(@MappingTarget ProductEntity productEntity, PatchProductInputDTO productDTO) {
        productDTO.setId(productEntity.getId());
        if (productDTO.getName() == null) productDTO.setName(productEntity.getName());
        if (productDTO.getDescription() == null) productDTO.setDescription(productEntity.getDescription());
        if (productDTO.getCompanyId() == null) productDTO.setCompanyId(productEntity.getCompanyId());
        if (productDTO.getBlocked() == null) productDTO.setBlocked(productEntity.getBlocked().toString());
        if (productDTO.getCategories() == null) productDTO.setCategories(Arrays.asList(productEntity.getCategories().split(";")));
        System.out.println(productDTO.getName());
        System.out.println(productDTO.getId());
        System.out.println(productDTO.getDescription());
        System.out.println(productDTO.getCompanyId());
        System.out.println(productDTO.getBlocked());
        System.out.println(productDTO.getCategories());
    }
    @Mapping(source="categories", target = "categories", qualifiedByName = "arrayToString")
    @Mapping(source="blocked", target = "blocked", qualifiedByName = "stringToBoolean")
    ProductEntity PatchDtoToEntity(@MappingTarget ProductEntity productEntity, PatchProductInputDTO productDTO);

    @Named("stringToArray")
    public static List<String> stringToArray(String categories) {
        return Arrays.asList(categories.split(";"));
    }

    @Named("arrayToString")
    public static String arrayToString(List<String> categories) {
        StringBuilder categoriesForEntity = new StringBuilder();
        for(String category: categories) {
            categoriesForEntity.append(category).append(";");
        }
        return categoriesForEntity.toString();
    }

    @Named("stringToBoolean")
    public static Boolean stringToBoolean(String booleanValue) {
        if(booleanValue.matches("true|1")) return true;
        return !booleanValue.matches("false|0");
    }

}
