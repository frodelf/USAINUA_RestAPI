package com.avadamedia.USAINUA.controllers;

import com.avadamedia.USAINUA.entity.Product;
import com.avadamedia.USAINUA.mapper.ProductMapper;
import com.avadamedia.USAINUA.models.ProductDTO;
import com.avadamedia.USAINUA.repositories.ProductsRepository;
import com.avadamedia.USAINUA.services.impl.ProductsServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/products")
@Tag(name = "Product controller", description = "Product API")
public class ProductController {
    private final ProductMapper productMapper;
    private final ProductsServiceImpl productsService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "authorized"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Resource not found."),

    })
    @Operation(summary = "Get all products")
    @GetMapping("/get-product/{page}")
    public List<ProductDTO> getAllProducts(
            @Parameter(description = "Page for pagination")
            @PathVariable("page")long page){
        if (page < 0) {
            throw new IllegalArgumentException("Число має бути більше 0");
        }
        List<Product> products = productsService.getAll();
        Page<Product> productsPage = new PageImpl<>(products, PageRequest.of((int)(page-1), 2), products.size());
        return productMapper.toDtoList(productsPage.getContent());
    }
}