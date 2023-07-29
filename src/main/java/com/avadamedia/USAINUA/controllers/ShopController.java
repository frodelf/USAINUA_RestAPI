package com.avadamedia.USAINUA.controllers;

import com.avadamedia.USAINUA.entity.Product;
import com.avadamedia.USAINUA.entity.Shop;
import com.avadamedia.USAINUA.mapper.ProductMapper;
import com.avadamedia.USAINUA.mapper.ShopMapper;
import com.avadamedia.USAINUA.models.ProductDTO;
import com.avadamedia.USAINUA.models.ShopDTO;
import com.avadamedia.USAINUA.repositories.ProductsRepository;
import com.avadamedia.USAINUA.repositories.ShopsRepository;
import com.avadamedia.USAINUA.repositories.StorageRepository;
import com.avadamedia.USAINUA.services.impl.ShopsServiceImpl;
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
@Tag(name = "Shop controller", description = "Shop API")
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/shops")
public class ShopController {
    private final ShopsServiceImpl shopsService;
    private final ShopMapper shopMapper;
    private final StorageRepository storageRepository;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "authorized"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Resource not found."),

    })
    @Operation(summary = "Get all shops")
    @GetMapping("/get-shop/{page}")
    public List<ShopDTO> getAllShops(
            @Parameter(description = "Page for pagination")
            @PathVariable("page")long page){
        if (page < 0) {
            throw new IllegalArgumentException("Число має бути більше 0");
        }
        List<Shop> shops = shopsService.getAll();
        Page<Shop> shopsPage = new PageImpl<>(shops, PageRequest.of((int)(page-1), 2), shops.size());
        return shopMapper.toDtoList(shopsPage.getContent());
    }
}
