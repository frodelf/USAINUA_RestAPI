package com.avadamedia.USAINUA.controllers;

import com.avadamedia.USAINUA.models.Products;
import com.avadamedia.USAINUA.models.Shops;
import com.avadamedia.USAINUA.models.Users;
import com.avadamedia.USAINUA.services.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/main/")
@RequiredArgsConstructor
public class MainController {
    private final AdditionalServicesService additionalServicesService;
    private final UsersService usersService;
    private final RolesService rolesService;
    private final ShopsService shopsService;
    private final ProductsService productsService;
    @PostMapping("/purchase-and-delivery/")
    public double purchaseAndDeliveryApproximatePrice(@RequestParam("transport")String transport,@RequestParam("additional-services")String additionalServices,
                                                      @RequestParam("weight")double weight, @RequestParam("price")double price){
        double approximatePrice = 0;
        try{
            approximatePrice+=additionalServicesService.getByName(additionalServices).getPrice();
        }catch (Exception e){

        }
        if(transport.equals("plane"))approximatePrice += 0.1*price+0.5*weight+1000;
        else if(transport.equals("ship"))approximatePrice += 0.05*price+0.3*weight+500;
        else approximatePrice += 800;
        return approximatePrice;
    }

    @PostMapping("/delivery/")
    public double deliveryApproximatePrice(@RequestParam("transport")String transport,@RequestParam("additional-services")String additionalServices,
                                           @RequestParam("weight")double weight){
        double approximatePrice = 0;
        try{
            approximatePrice+=additionalServicesService.getByName(additionalServices).getPrice();
        }catch (Exception e){
        }
        if(transport.equals("plane"))approximatePrice += 0.5*weight+1000;
        else if(transport.equals("ship"))approximatePrice += 0.3*weight+500;
        else approximatePrice += 800;
        return approximatePrice;
    }
    @GetMapping("/shops/")
    public List<Shops> getAllShops(){
        return shopsService.getAll();
    }
    @GetMapping("/products/")
    public List<Products> getAllProducts(){
        return productsService.getAll();
    }

}
