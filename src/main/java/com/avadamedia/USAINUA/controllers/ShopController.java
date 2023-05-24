package com.avadamedia.USAINUA.controllers;

import com.avadamedia.USAINUA.entity.Product;
import com.avadamedia.USAINUA.entity.Shop;
import com.avadamedia.USAINUA.mapper.ProductMapper;
import com.avadamedia.USAINUA.mapper.ShopMapper;
import com.avadamedia.USAINUA.models.ProductDTO;
import com.avadamedia.USAINUA.models.ShopDTO;
import com.avadamedia.USAINUA.repositories.ProductsRepository;
import com.avadamedia.USAINUA.repositories.ShopsRepository;
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
@Tag(name = "Shop controller", description = "Shop API")
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/shops/")
public class ShopController {
    private final ShopsRepository shopsRepository;
    private final ShopMapper shopMapper;
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
    })
    @Operation(summary = "Get shops")
    @GetMapping("/get-shop/{id}")
    public List<ShopDTO> getAllShops(
            @Parameter(description = "shop's ID")
            @PathVariable("id")long id){
        Page<Shop> shopsPage = shopsRepository.findAll(PageRequest.of((int)(id-1), 2));
        return shopMapper.toDtoList(shopsPage.getContent());
    }
}
