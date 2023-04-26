package com.avadamedia.USAINUA.controllers;

import com.avadamedia.USAINUA.entity.AdditionalServices;
import com.avadamedia.USAINUA.entity.Products;
import com.avadamedia.USAINUA.entity.Shops;
import com.avadamedia.USAINUA.mapper.ProductMapper;
import com.avadamedia.USAINUA.mapper.ShopMapper;
import com.avadamedia.USAINUA.models.ShopDTO;
import com.avadamedia.USAINUA.repositories.ProductsRepository;
import com.avadamedia.USAINUA.repositories.ShopsRepository;
import io.swagger.v3.oas.annotations.Operation;
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
    public double purchaseAndDeliveryApproximatePrice(@RequestParam("transport")String transport,@RequestParam("additional-services")List<AdditionalServices> additionalServices,
                                                      @RequestParam("weight")double weight, @RequestParam("price")double price){
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
    public double deliveryApproximatePrice(@RequestParam("transport")String transport,@RequestParam("additional-services")List<AdditionalServices> additionalServices,
                                           @RequestParam("weight")double weight){
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
//    @GetMapping("/shops/{id}")
//    public List<ShopDTO> getAllShops(@PathVariable("id")long id){
//        Page<Shops> page = shopsRepository.findAll(PageRequest.of((int) (id-1), 2));
//        List<Shops> shopsList = page.getContent();
//        log.info(shopsList);
//        return shopMapper.toDtoList(shopsList);
//    }
    @Operation(summary = "Get shops")
    @GetMapping("/shops/{id}")
    public Page<Shops> getAllShops(@PathVariable("id")long id){
        return shopsRepository.findAll(PageRequest.of((int)(id-1), 2));
    }
    @Operation(summary = "Get products")
    @GetMapping("/products/{id}")
    public Page<Products> getAllProducts(@PathVariable("id")long id){
        return productsRepository.findAll(PageRequest.of((int)(id-1), 2));
    }


}
