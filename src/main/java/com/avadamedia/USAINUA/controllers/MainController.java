package com.avadamedia.USAINUA.controllers;

import com.avadamedia.USAINUA.entity.AdditionalServices;
import com.avadamedia.USAINUA.entity.Products;
import com.avadamedia.USAINUA.entity.Shops;
import com.avadamedia.USAINUA.mapper.ProductMapper;
import com.avadamedia.USAINUA.mapper.ShopMapper;
import com.avadamedia.USAINUA.models.ProductDTO;
import com.avadamedia.USAINUA.models.ShopDTO;
import com.avadamedia.USAINUA.repositories.ProductsRepository;
import com.avadamedia.USAINUA.repositories.ShopsRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@RestController
@RequiredArgsConstructor
@Tag(name = "Main page controller", description = "Main page API")
public class MainController {
    private final ShopsRepository shopsRepository;
    private final ProductsRepository productsRepository;
    private final ShopMapper shopMapper;
    private final ProductMapper productMapper;

    @Operation(summary = "Purchase and delivery approximate price")
    @PostMapping("/purchase-and-delivery/")
    public double purchaseAndDeliveryApproximatePrice(@Parameter(description = "Transport for approximate shipping cost") @RequestParam("transport")String transport,
                                                      @Parameter(description = "Additional services list for approximate shipping cost") @RequestParam("additional-services")List<AdditionalServices> additionalServices,
                                                      @Parameter(description = "Weight for approximate shipping cost") @RequestParam("weight")double weight,
                                                      @Parameter(description = "Products' price for approximate shipping cost") @RequestParam("price")double price){
        double approximatePrice = 0;
        try{
            for (AdditionalServices additionalService : additionalServices) {
                approximatePrice+=additionalService.getPrice();
            }
        }catch (Exception e){
        }
        if(transport.equals("plane"))approximatePrice += 0.1*price+0.5*weight+1000;
        else if(transport.equals("ship"))approximatePrice += 0.05*price+0.3*weight+500;
        else approximatePrice += 800;
        return approximatePrice;
    }

    @Operation(summary = "Delivery approximate price")
    @PostMapping("/delivery/")
    public double deliveryApproximatePrice(@Parameter(description = "Transport for approximate shipping cost") @RequestParam("transport")String transport,
                                           @Parameter(description = "Additional services list for approximate shipping cost") @RequestParam("additional-services")List<AdditionalServices> additionalServices,
                                           @Parameter(description = "Weight for approximate shipping cost") @RequestParam("weight")double weight){
        double approximatePrice = 0;
        try{
            for (AdditionalServices additionalService : additionalServices) {
                approximatePrice+=additionalService.getPrice();
            }
        }catch (Exception e){
        }
        if(transport.equals("plane"))approximatePrice += 0.5*weight+1000;
        else if(transport.equals("ship"))approximatePrice += 0.3*weight+500;
        else approximatePrice += 800;
        return approximatePrice;
    }
    @Operation(summary = "Get shops")
    @GetMapping("/shops/{id}")
    public List<ShopDTO> getAllShops(@Parameter(description = "ID of the page requested for shops") @PathVariable("id")long id){
        Page<Shops> shopsPage = shopsRepository.findAll(PageRequest.of((int)(id-1), 2));
        return shopMapper.toDtoList(shopsPage.getContent());
    }
    @Operation(summary = "Get products")
    @GetMapping("/products/{id}")
    public List<ProductDTO> getAllProducts(@Parameter(description = "ID of the page requested for products") @PathVariable("id")long id){
        Page<Products> productsPage = productsRepository.findAll(PageRequest.of((int)(id-1), 2));
        return productMapper.toDtoList(productsPage.getContent());
    }


}
