package com.avadamedia.USAINUA.controllers;

import com.avadamedia.USAINUA.entity.*;
import com.avadamedia.USAINUA.mapper.OrderMapper;
import com.avadamedia.USAINUA.mapper.UserAddressMapper;
import com.avadamedia.USAINUA.models.OrderDTO;
import com.avadamedia.USAINUA.models.UserAddressDTO;
import com.avadamedia.USAINUA.services.impl.*;
import javax.validation.Valid;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders/")
@RequiredArgsConstructor
@Tag(name = "Order Controller", description = "Orders API")
@SecurityRequirement(name = "bearerAuth")
public class OrdersController {
    private final OrdersServiceImpl ordersService;
    private final UsersServiceImpl usersService;
    private final UsersAddressServiceImpl usersAddressService;
    private final UserAddressMapper userAddressMapper;
    private final OrderMapper orderMapper;

    @GetMapping("get-orders")
    @Operation(summary = "Get all orders")
    public List<OrderDTO> getAllOrders(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return orderMapper.toDtoList(usersService.getByEmail(authentication.getName()).getOrders());
    }
    @PostMapping("edit-users-address/{id}")
    @Operation(summary = "Edit user's address")
    public void editUsersAddress(
            @Parameter(description = "User address id")
            @PathVariable("id")Long id,
                                 @RequestBody UserAddressDTO addressDTO){
        UsersAddress address = userAddressMapper.toEntity(addressDTO);
        address.setId(id);
        usersAddressService.save(address);
    }
    @GetMapping("order/{id}")
    @Operation(summary = "Get order by id")
    public OrderDTO getOrderById(
            @Parameter(description = "Order's id")
            @PathVariable("id")Long id){
        return orderMapper.toDto(ordersService.getById(id));
    }
    @PostMapping("pay-order/{id}")
    @Operation(summary = "Pay for the order")
    public void payOrder(
            @Parameter(description = "Order's id")
            @PathVariable("id")Long id){
        ordersService.payOrder(id);
    }
    @PostMapping("make-order")
    @Operation(summary = "Make order")
    public void addOrder(@RequestBody @Valid OrderDTO orderDTO,
                         @Parameter(description = "List of id for additional services")
                         @RequestParam("additional-services") List<Long> idAdditionalServices,
                         @Parameter(description = "User address id")
                             @RequestParam("id-address")Long idAddress){
        ordersService.addOrder(orderDTO, idAdditionalServices, idAddress);
    }
}
