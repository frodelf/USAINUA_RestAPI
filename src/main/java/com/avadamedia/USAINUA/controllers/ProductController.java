package com.avadamedia.USAINUA.controllers;

import com.avadamedia.USAINUA.entity.Product;
import com.avadamedia.USAINUA.mapper.ProductMapper;
import com.avadamedia.USAINUA.models.ProductDTO;
import com.avadamedia.USAINUA.repositories.ProductsRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/products/")
@Tag(name = "Product controller", description = "Product API")
public class ProductController {
    private final ProductMapper productMapper;
    private final ProductsRepository productsRepository;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
    })
    @Operation(summary = "Get products")
    @GetMapping("/get-product/{id}")
    public List<ProductDTO> getAllProducts(
            @Parameter(description = "product's ID")
            @PathVariable("id")long id){
        Page<Product> productsPage = productsRepository.findAll(PageRequest.of((int)(id-1), 2));
        return productMapper.toDtoList(productsPage.getContent());
    }
}
