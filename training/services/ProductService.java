package com.milankas.training.services;

import com.milankas.training.Mappers.ProductMapperInterface;
import com.milankas.training.dtos.input.PatchProductInputDTO;
import com.milankas.training.dtos.input.PostProductInputDTO;
import com.milankas.training.dtos.output.ProductOutputDTO;
import com.milankas.training.persistance.entities.ProductEntity;
import com.milankas.training.persistance.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductService implements ProductServiceInterface {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductMapperInterface productMapper;

    @Override
    public List<ProductOutputDTO> findAllProducts() {
        List<ProductEntity> productsReceived = productRepository.findAll();
        List<ProductOutputDTO> productsToSend = new ArrayList();
        productsReceived.forEach(product -> {
            productsToSend.add(this.productMapper.EntityToDto(product));
        });
        return productsToSend;
    }

    @Override
    public ProductOutputDTO findProductById(UUID productId) {
        ProductEntity productReceived = productRepository.findById(productId).orElse(null);
        return productMapper.EntityToDto(productReceived);
    }

    @Override
    public ProductOutputDTO saveProduct(PostProductInputDTO productReceived) {
        ProductEntity productToSave = productMapper.PostDtoToEntity(productReceived);
        return productMapper.EntityToDto(productRepository.save(productToSave));
    }

    @Override
    public ProductOutputDTO updateProductById(UUID productId, PatchProductInputDTO productForUpdate) {
        ProductEntity productFound = productRepository.findById(productId).orElse(null);
        if (productFound == null) return null;
//        if (productForUpdate.getName() != null) productFound.setName(productForUpdate.getName());
//        if (productForUpdate.getDescription() != null) productFound.setDescription(productForUpdate.getDescription());
//        if (productForUpdate.getCompanyId() != null) productFound.setCompanyId(productForUpdate.getCompanyId());
//        if (productForUpdate.getBlocked() != null) {
//            if (productForUpdate.getBlocked().matches("true|1"))productFound.setBlocked(true);
//            if (productForUpdate.getBlocked().matches("false|0"))productFound.setBlocked(false);
//        };
//        if (productForUpdate.getCategories() != null) {
//            StringBuilder categoriesForEntity = new StringBuilder();
//            for(String category: productForUpdate.getCategories()) {
//                categoriesForEntity.append(category).append(";");
//            }
//            productFound.setCategories(categoriesForEntity.toString());
//        };
//        final ProductEntity updatedProduct = productRepository.save(productFound);
//        return productMapper.EntityToDto(updatedProduct);
        ProductEntity productToSave = productMapper.PatchDtoToEntity(productFound, productForUpdate);
        return productMapper.EntityToDto(productRepository.save(productToSave));
    }

    @Override
    public Boolean deleteProductById(UUID productId) {
        ProductEntity productFound = productRepository.findById(productId).orElse( null);
        if (productFound == null) return null;
        productRepository.delete(productFound);
        return true;
    }

}




{
    "userId": "f0c22621-5c07-4130-ba19-d412e7937593",
    "lineItems": [
        {
            "id": "2d2bad25-42c2-49da-af5a-c9ccc7022149",
            "qty": 387
        },
        {
            "id": "1fd2e263-76cd-4036-b8ef-aee1fd8284b7",
            "qty": 11
        },
        {
            "id": "848836c6-8291-48a0-9a6c-086efdabe303",
            "qty": 593
        }
    ],
    "emailAddress": "Tyreek_Hand@gmail.com",
    "shopAddress": {
        "id": "7e21aae4-73ec-4512-af74-59671908f395",
        "addressLine1": "257 Garrick Brooks Apt. 268",
        "addressLine2": "77750 Garnett Mall Suite 449",
        "contactName": "Elmira Cummings",
        "contactPhone": "1-158-576-4239 x09622",
        "state": "Rhode Island",
        "city": "Port Sophia",
        "zipCode": "01227-1814",
        "countryCode": "FI"
    }
}

{
    "userId": "ec2f7eba-c4b3-42ef-974e-66e37f18e3db",
    "lineItems": [
        {
            "id": "c4352885-e00d-47d7-8ed7-9296c0449958",
            "qty": 8
        },
        {
            "id": "bf1454af-f7f2-40cb-a58b-b51e456f5ebd",
            "qty": 679
        },
        {
            "id": "a524ca53-889b-4259-a842-4f38f6397f56",
            "qty": 400
        }
    ],
    "emailAddress": "Kaylie_Nienow11@yahoo.com",
    "shopAddress": {
        "id": "2490a17c-429a-40d3-b162-3e7943028c71",
        "addressLine1": "373 Carroll Lakes Suite 664",
        "addressLine2": "674 Cecilia Brooks Apt. 293",
        "contactName": "Cruz Jenkins",
        "contactPhone": "(282) 720-0792",
        "state": "Nebraska",
        "city": "West Ahmedtown",
        "zipCode": "79534",
        "countryCode": "PK"
    }
}