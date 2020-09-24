package com.milankas.training.controllers;

import javax.validation.Valid;

import com.milankas.training.dtos.input.PatchProductInputDTO;
import com.milankas.training.dtos.input.PostProductInputDTO;
import com.milankas.training.dtos.output.ProductOutputDTO;
import com.milankas.training.exceptions.InvalidParamException;
import com.milankas.training.exceptions.ResourceNotFoundException;
import com.milankas.training.services.ProductServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1")
public class ProductController {

	@Autowired
	private ProductServiceInterface productService;

	@GetMapping("/products")
	public ResponseEntity<List<ProductOutputDTO>> getAllProducts() {
		return ResponseEntity.ok(productService.findAllProducts());
	}

	@GetMapping("/products/{id}")
	public ResponseEntity<ProductOutputDTO> getProductById(@Valid @PathVariable(value = "id") UUID productId) throws ResourceNotFoundException {
		ProductOutputDTO productFound = productService.findProductById(productId);
		if (productFound == null) throw new ResourceNotFoundException("Product not found for id: " + productId);
		return ResponseEntity.ok(productFound);
	}

	@PostMapping("/products")
	public ResponseEntity<ProductOutputDTO> createProduct(@Valid @RequestBody PostProductInputDTO productToSave) throws InvalidParamException {
		return new ResponseEntity<>(productService.saveProduct(productToSave), HttpStatus.CREATED);
	}

	@PatchMapping("/products/{id}")
	public ResponseEntity<ProductOutputDTO> updateProduct(@Valid @PathVariable(value = "id") UUID productId, @Valid @RequestBody PatchProductInputDTO productForUpdate) throws ResourceNotFoundException {
		ProductOutputDTO productUpdated = productService.updateProductById(productId, productForUpdate);
		if (productUpdated == null) throw new ResourceNotFoundException("Product not found for id: " + productId);
		return new ResponseEntity<>(productUpdated, HttpStatus.OK);
	}

	@DeleteMapping("/products/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable(value = "id") UUID productId) throws ResourceNotFoundException {
		Boolean productDeleted = productService.deleteProductById(productId);
		if (productDeleted == null) throw new ResourceNotFoundException("Product not found for id: " + productId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
    
}
