package com.avadamedia.USAINUA.services.impl;

import com.avadamedia.USAINUA.entity.AdditionalService;
import com.avadamedia.USAINUA.entity.Order;
import com.avadamedia.USAINUA.entity.User;
import com.avadamedia.USAINUA.entity.UsersAddress;
import com.avadamedia.USAINUA.enums.Status;
import com.avadamedia.USAINUA.models.OrderDTO;
import com.avadamedia.USAINUA.repositories.*;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class OrdersServiceImplTest {
    @MockBean
    private OrdersRepository ordersRepository;
    @MockBean
    private UsersRepository usersRepository;
    @Autowired
    private OrdersServiceImpl ordersService;
    @MockBean
    private UsersAddressRepository usersAddressRepository;
    @MockBean
    private AdditionalServicesRepository additionalServicesRepository;
    @MockBean
    private FinancesRepository financesRepository;

    @Test
    void save() {
        Order order = new Order();
        order.setTotalPrice(1234.5);
        ordersService.save(order);
        verify(ordersRepository, times(1)).save(order);
    }

    @Test
    void getById() {
        Order order = new Order();
        order.setId(1L);
        when(ordersRepository.findById(1L)).thenReturn(Optional.of(order));
        Order result = ordersService.getById(1L);
    }

    @Test
    @WithMockUser(username = "derkach2007artem@gmail.com")
    void payOrder() {
        Order order = new Order();
        order.setId(1L);
        order.setTotalPrice(100.0);
        order.setStatus(Status.READY_FOR_PAYMENT);

        User user = new User();
        user.setEmail("derkach2007artem@gmail.com");
        user.setMoney(200.0);
        user.setFinances(new ArrayList<>());

        when(ordersRepository.findById(1L)).thenReturn(Optional.of(order));
        when(usersRepository.findByEmail("derkach2007artem@gmail.com")).thenReturn(Optional.of(user));

        ordersService.payOrder(1L);

        assertEquals(Status.PAID, order.getStatus());
        assertEquals(100.0, user.getMoney());
        assertEquals(1, user.getFinances().size());
    }

    @Test
    @WithMockUser(username = "derkach2007artem@gmail.com")
    void addOrder() {
        User user = new User();
        user.setEmail("derkach2007artem@gmail.com");
        user.setOrders(new ArrayList<>());

        OrderDTO orderDTO = new OrderDTO();

        UsersAddress usersAddress = new UsersAddress();
        usersAddress.setAddressName("name");

        List<AdditionalService> additionalServices = new ArrayList<>();
        additionalServices.add(new AdditionalService());

        Long idAddress = 1L;
        List<Long> idAdditionalServices = new ArrayList<>();
        idAdditionalServices.add(1L);

        when(usersRepository.findByEmail("derkach2007artem@gmail.com")).thenReturn(Optional.of(user));
        when(usersAddressRepository.findById(idAddress)).thenReturn(Optional.of(usersAddress));
        when(additionalServicesRepository.findById(anyLong())).thenReturn(Optional.of(additionalServices.get(0)));

        ordersService.addOrder(orderDTO, idAdditionalServices, idAddress);

        verify(usersRepository, times(1)).save(user);
        verify(additionalServicesRepository, times(idAdditionalServices.size())).findById(anyLong());
        verify(usersAddressRepository, times(1)).findById(idAddress);
        verify(ordersRepository, times(1)).save(any());

        assertEquals(1, user.getOrders().size());
    }
}