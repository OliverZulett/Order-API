package com.milankas.training.services;

import com.milankas.training.dtos.input.PatchProductInputDTO;
import com.milankas.training.dtos.input.PostProductInputDTO;
import com.milankas.training.dtos.output.ProductOutputDTO;

import java.util.List;
import java.util.UUID;

public interface ProductServiceInterface {

    List<ProductOutputDTO> findAllProducts();

    ProductOutputDTO findProductById(UUID id);

    ProductOutputDTO saveProduct(PostProductInputDTO product);

    ProductOutputDTO updateProductById(UUID id, PatchProductInputDTO productForUpdate);

    Boolean deleteProductById(UUID id);

}
