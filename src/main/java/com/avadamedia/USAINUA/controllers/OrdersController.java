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
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
@Tag(name = "Order Controller", description = "Orders API")
@SecurityRequirement(name = "bearerAuth")
public class OrdersController {
    private final OrdersServiceImpl ordersService;
    private final UsersServiceImpl usersService;
    private final UsersAddressServiceImpl usersAddressService;
    private final UserAddressMapper userAddressMapper;
    private final OrderMapper orderMapper;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "authorized"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Resource not found."),

    })
    @GetMapping("/get-all-orders/{page}")
    @Operation(summary = "Get all orders")
    public List<OrderDTO> getAllOrders(
            @Parameter(description = "Page for pagination")
            @PathVariable("page")long page){
        if (page < 0) {
            throw new IllegalArgumentException("Число має бути більше 0");
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        List<Order> orders = usersService.getByEmail(authentication.getName()).getOrders();
        Page<Order> orderPage = new PageImpl<>(orders, PageRequest.of((int)(page-1), 2), orders.size());
        return orderMapper.toDtoList(orderPage.getContent());
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "authorized"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Resource not found."),
    })
    @GetMapping("/order/{id}")
    @Operation(summary = "Get order by id")
    public OrderDTO getOrderById(
            @Parameter(description = "Order's id")
            @PathVariable("id")Long id){
        if (id < 0) {
            throw new IllegalArgumentException("ID має бути більше 0");
        }
        return orderMapper.toDto(ordersService.getById(id));
    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "authorized"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Resource not found."),

    })
    @PostMapping("/pay-order/{id}")
    @Operation(summary = "Pay for the order")
    public void payOrder(
            @Parameter(description = "Order's id")
            @PathVariable("id")Long id){
        ordersService.payOrder(id);
    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "authorized"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Resource not found."),

    })
    @PostMapping("/make-order")
    @Operation(summary = "Make order")
    public void addOrder(@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Order body for the user") @RequestBody @Valid OrderDTO orderDTO,
                         @Parameter(description = "List of id for additional services")
                         @RequestParam("additional-services") List<Long> idAdditionalServices,
                         @Parameter(description = "User address id") @RequestParam("id-address")Long idAddress){
        ordersService.addOrder(orderDTO, idAdditionalServices, idAddress);
    }
}
