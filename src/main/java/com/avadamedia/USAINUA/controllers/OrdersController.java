package com.avadamedia.USAINUA.controllers;

import com.avadamedia.USAINUA.entity.*;
import com.avadamedia.USAINUA.enums.Status;
import com.avadamedia.USAINUA.mapper.OrderMapper;
import com.avadamedia.USAINUA.mapper.UserAddressMapper;
import com.avadamedia.USAINUA.models.OrderDTO;
import com.avadamedia.USAINUA.models.UserAddressDTO;
import com.avadamedia.USAINUA.repositories.FinancesRepository;
import com.avadamedia.USAINUA.services.impl.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/orders/")
@RequiredArgsConstructor
@Tag(name = "Order Controller", description = "Orders API")
public class OrdersController {
    private final OrdersServiceImpl ordersService;
    private final FinancesServiceImpl financesService;
    private final UsersServiceImpl usersService;
    private final UsersAddressServiceImpl usersAddressService;
    private final AdditionalServicesServiceImpl additionalServicesService;
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
    public void editUsersAddress(@PathVariable("id")Long id, @RequestBody UserAddressDTO addressDTO){
        UsersAddress address = userAddressMapper.toEntity(addressDTO);
        address.setId(id);
        usersAddressService.save(address);
    }
    @GetMapping("order/{id}")
    @Operation(summary = "Get order by id")
    public OrderDTO getOrderById(@PathVariable("id")Long id){
        return orderMapper.toDto(ordersService.getById(id));
    }
    @PostMapping("pay-order/{id}")
    @Operation(summary = "Pay for the order")
    public void payOrder(@PathVariable("id")Long id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Orders orders = ordersService.getById(id);
        Users user = usersService.getByEmail(authentication.getName());
        if(user.getMoney()-orders.getTotalPrice() >= 0){
            user.setMoney(user.getMoney()-orders.getTotalPrice());
            orders.setStatus(Status.PAID);
            orders.setDateReceiving(Date.valueOf(LocalDate.now()));
            Finances finances = new Finances(orders.getDateReceiving(), orders.getTotalPrice());
            financesService.save(finances);
            user.getFinances().add(finances);
            ordersService.save(orders);
            usersService.save(user);
        }else {
            throw new RuntimeException();
        }
    }
    @PostMapping("make-order")
    @Operation(summary = "Make order")
    public void addOrder(@RequestBody @Valid OrderDTO orderDTO,
                                                  @RequestParam("additional-services") List<Long> idAdditionalServices,
                                                  @RequestParam("id-address")Long idAddress){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Users user = usersService.getByEmail(authentication.getName());
        UsersAddress address = usersAddressService.getById(idAddress);
        Orders order = orderMapper.toEntity(orderDTO);
        List<AdditionalServices> additionalServices = new ArrayList<>();
        for (Long aLong : idAdditionalServices) {
            additionalServices.add(additionalServicesService.getById(aLong));
        }
        order.setUsersAddresses(address);
        order.setStatus(Status.CALCULATING_VALUE);
        order.setDataRegistration(Date.valueOf(LocalDate.now()));
        order.setAdditionalServices(additionalServices);
        ordersService.save(order);
        user.getOrders().add(order);
        usersService.save(user);
    }
}
