package com.avadamedia.USAINUA.services.impl;

import com.avadamedia.USAINUA.entity.*;
import com.avadamedia.USAINUA.enums.Status;
import com.avadamedia.USAINUA.mapper.OrderMapper;
import com.avadamedia.USAINUA.models.OrderDTO;
import com.avadamedia.USAINUA.repositories.OrdersRepository;
import com.avadamedia.USAINUA.services.OrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrdersServiceImpl implements OrdersService {
    private final OrdersRepository ordersRepository;
    private final UsersServiceImpl usersService;
    private final FinancesServiceImpl financesService;
    private final UsersAddressServiceImpl usersAddressService;
    private final AdditionalServicesServiceImpl additionalServicesService;
    private final OrderMapper orderMapper;
    public void save(Order orders){ordersRepository.save(orders);}
    public Order getById(long id){return ordersRepository.findById(id).get();}
    public void payOrder(Long id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Order orders = getById(id);
        User user = usersService.getByEmail(authentication.getName());
        if(user.getMoney()-orders.getTotalPrice() >= 0){
            user.setMoney(user.getMoney()-orders.getTotalPrice());
            orders.setStatus(Status.PAID);
            orders.setDateReceiving(Date.valueOf(LocalDate.now()));
            Finances finances = new Finances(orders.getDateReceiving(), orders.getTotalPrice());
            financesService.save(finances);
            user.getFinances().add(finances);
            save(orders);
            usersService.save(user);
        }else {
            throw new RuntimeException("You don't have enough money");
        }
    }

    public void addOrder(OrderDTO orderDTO, List<Long> idAdditionalServices, Long idAddress){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = usersService.getByEmail(authentication.getName());
        UsersAddress address = usersAddressService.getById(idAddress);
        Order order = orderMapper.toEntity(orderDTO);
        List<AdditionalService> additionalServices = new ArrayList<>();
        for (Long aLong : idAdditionalServices) {
            additionalServices.add(additionalServicesService.getById(aLong));
        }
        order.setUsersAddresses(address);
        order.setStatus(Status.CALCULATING_VALUE);
        order.setDataRegistration(Date.valueOf(LocalDate.now()));
        order.setAdditionalServices(additionalServices);
        save(order);
        user.getOrders().add(order);
        usersService.save(user);
    }
}
