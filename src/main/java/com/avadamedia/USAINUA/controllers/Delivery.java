package com.avadamedia.USAINUA.controllers;

import com.avadamedia.USAINUA.entity.AdditionalService;
import com.avadamedia.USAINUA.models.CreditCardDTO;
import com.avadamedia.USAINUA.models.DeliveryDTO;
import com.avadamedia.USAINUA.util.CalculatorUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@RestController
@RequiredArgsConstructor
@Tag(name = "Main controller", description = "Main API")
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/approximate-price")
public class Delivery {
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "authorized"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Resource not found."),
    })
    @Operation(summary = "Purchase and delivery approximate price")
    @PostMapping("/purchase-and-delivery")
    public double purchaseAndDeliveryApproximatePrice(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "credit card body for the user")@RequestBody @Valid DeliveryDTO deliveryDTO,
            @Parameter(description = "Products' price for approximate shipping cost")
            @RequestParam("price")double price){
        return CalculatorUtil.purchaseAndDeliveryApproximatePrice(deliveryDTO.getWeight(), deliveryDTO.getAdditionalServices(), deliveryDTO.getTransport(), price);
    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "authorized"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Resource not found."),
    })
    @Operation(summary = "Delivery approximate price")
    @PostMapping("/delivery")
    public double deliveryApproximatePrice(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "credit card body for the user")@RequestBody @Valid DeliveryDTO deliveryDTO){
        return CalculatorUtil.deliveryApproximatePrice(deliveryDTO.getWeight(), deliveryDTO.getAdditionalServices(), deliveryDTO.getTransport());
    }
}