package com.avadamedia.USAINUA.controllers;

import com.avadamedia.USAINUA.entity.AdditionalService;
import com.avadamedia.USAINUA.entity.Order;
import com.avadamedia.USAINUA.entity.User;
import com.avadamedia.USAINUA.entity.UsersAddress;
import com.avadamedia.USAINUA.enums.Status;
import com.avadamedia.USAINUA.models.OrderDTO;
import com.avadamedia.USAINUA.models.UserAddressDTO;
import com.avadamedia.USAINUA.repositories.*;
import com.avadamedia.USAINUA.services.impl.OrdersServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class OrderControllerTest {

    @Autowired
    private OrdersServiceImpl ordersService;
    @Autowired
    private OrdersController ordersController;
    @MockBean
    private UsersRepository usersRepository;
    @MockBean
    private UsersAddressRepository usersAddressRepository;
    @MockBean
    private AdditionalServicesRepository additionalServicesRepository;
    @MockBean
    private FinancesRepository financesRepository;
    @MockBean
    private OrdersRepository ordersRepository;

    @Test
    @WithMockUser(username = "derkach2007artem@gmail.com")
    void getAllOrders() {
        Order orders1 = new Order();
        Order orders2 = new Order();

        orders1.setPrice(10.0);
        orders2.setPrice(12.0);

        orders1.setDescription("order 1");
        orders2.setDescription("order 2");

        List<Order> orders = new ArrayList<>();
        orders.add(orders1);
        orders.add(orders2);

        User user = new User();
        user.setId(1L);
        user.setOrders(orders);

        when(usersRepository.findByEmail(anyString())).thenReturn(Optional.of(user));

        List<OrderDTO> orderDTO = ordersController.getAllOrders(1);

        assertEquals(2, orderDTO.size());
        assertEquals(10, orderDTO.get(0).getPrice());
        assertEquals("order 1", orderDTO.get(0).getDescription());
        assertEquals(12, orderDTO.get(1).getPrice());
        assertEquals("order 2", orderDTO.get(1).getDescription());
    }

    @Test
    void getOrderById() {
        long id = 1;
        Order orders = new Order();
        orders.setId(id);
        orders.setPrice(10.0);
        orders.setDescription("order 1");

        when(ordersRepository.findById(anyLong())).thenReturn(Optional.of(orders));

        OrderDTO orderDTO = ordersController.getOrderById(id);

        assertEquals("order 1", orderDTO.getDescription());
        assertEquals(10.0, orderDTO.getPrice());
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

        ordersController.payOrder(1L);

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

        ordersController.addOrder(orderDTO, idAdditionalServices, idAddress);

        verify(usersRepository, times(1)).save(user);
        verify(additionalServicesRepository, times(idAdditionalServices.size())).findById(anyLong());
        verify(usersAddressRepository, times(1)).findById(idAddress);
        verify(ordersRepository, times(1)).save(any());

        assertEquals(1, user.getOrders().size());
    }

}