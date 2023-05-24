package com.avadamedia.USAINUA.controllers;

import com.avadamedia.USAINUA.entity.AdditionalService;
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
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequiredArgsConstructor
@Tag(name = "Main controller", description = "Main API")
@SecurityRequirement(name = "bearerAuth")
public class MainController {
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
    })
    @Operation(summary = "Purchase and delivery approximate price")
    @PostMapping("/approximate-price/purchase-and-delivery/")
    public double purchaseAndDeliveryApproximatePrice(
            @Parameter(description = "Transport for approximate shipping cost")
            @RequestParam("transport")String transport,
            @Parameter(description = "Additional services list for approximate shipping cost")
            @RequestParam("additional-services")List<AdditionalService> additionalServices,
            @Parameter(description = "Weight for approximate shipping cost")
            @RequestParam("weight")double weight,
            @Parameter(description = "Products' price for approximate shipping cost")
            @RequestParam("price")double price){
        double approximatePrice = 0;

        for (AdditionalService additionalService : additionalServices) {
        approximatePrice+=additionalService.getPrice();
        }
        if(transport.equals("plane"))approximatePrice += 0.1*price+0.5*weight+1000;
        else if(transport.equals("ship"))approximatePrice += 0.05*price+0.3*weight+500;
        else approximatePrice += 800;
        return approximatePrice;
    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
    })
    @Operation(summary = "Delivery approximate price")
    @PostMapping("/approximate-price/delivery/")
    public double deliveryApproximatePrice(
            @Parameter(description = "Transport for approximate shipping cost")
            @RequestParam("transport")String transport,
            @Parameter(description = "Additional services list for approximate shipping cost")
            @RequestParam("additional-services")List<AdditionalService> additionalServices,
            @Parameter(description = "Weight for approximate shipping cost")
            @RequestParam("weight")double weight){
        double approximatePrice = 0;
        for (AdditionalService additionalService : additionalServices) {
        approximatePrice+=additionalService.getPrice();}
        if(transport.equals("plane"))approximatePrice += 0.5*weight+1000;
        else if(transport.equals("ship"))approximatePrice += 0.3*weight+500;
        else approximatePrice += 800;
        return approximatePrice;
    }
}
