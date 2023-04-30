package com.avadamedia.USAINUA.controllers;

import com.avadamedia.USAINUA.entity.AdditionalServices;
import com.avadamedia.USAINUA.entity.Orders;
import com.avadamedia.USAINUA.entity.Users;
import com.avadamedia.USAINUA.entity.UsersAddress;
import com.avadamedia.USAINUA.enums.Status;
import com.avadamedia.USAINUA.models.OrderDTO;
import com.avadamedia.USAINUA.models.UserAddressDTO;
import com.avadamedia.USAINUA.repositories.AdditionalServicesRepository;
import com.avadamedia.USAINUA.repositories.OrdersRepository;
import com.avadamedia.USAINUA.repositories.UsersAddressRepository;
import com.avadamedia.USAINUA.repositories.UsersRepository;
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
class OrdersControllerTest {

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
    private OrdersRepository ordersRepository;

    @Test
    @WithMockUser(username = "derkach2007artem@gmail.com")
    void getAllOrders() {
        Orders orders1 = new Orders();
        Orders orders2 = new Orders();

        orders1.setPrice(10.0);
        orders2.setPrice(12.0);

        orders1.setDescription("order 1");
        orders2.setDescription("order 2");

        List<Orders> orders = new ArrayList<>();
        orders.add(orders1);
        orders.add(orders2);

        Users users = new Users();
        users.setId(1L);
        users.setOrders(orders);

        when(usersRepository.findByEmail(anyString())).thenReturn(Optional.of(users));

        List<OrderDTO> orderDTO = ordersController.getAllOrders();

        assertEquals(2, orderDTO.size());
        assertEquals(10, orderDTO.get(0).getPrice());
        assertEquals("order 1", orderDTO.get(0).getDescription());
        assertEquals(12, orderDTO.get(1).getPrice());
        assertEquals("order 2", orderDTO.get(1).getDescription());
    }


    @Test
    void editUsersAddress() {
        UserAddressDTO userAddressDTO = new UserAddressDTO();
        userAddressDTO.setAddressName("home");
        userAddressDTO.setUsersName("edit");
        userAddressDTO.setUsersSurname("edit");
        userAddressDTO.setCity("City");
        userAddressDTO.setPhone("098789087");
        userAddressDTO.setDepartment("qwerty");
        userAddressDTO.setRegion("weqr");

        ordersController.editUsersAddress(1L, userAddressDTO);

        verify(usersAddressRepository, times(1)).save(any());
    }

    @Test
    void getOrderById() {
        long id = 1;
        Orders orders = new Orders();
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
        Orders order = new Orders();
        order.setId(1L);
        order.setTotalPrice(100.0);
        order.setStatus(Status.READY_FOR_PAYMENT);

        Users user = new Users();
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
        Users user = new Users();
        user.setEmail("derkach2007artem@gmail.com");
        user.setOrders(new ArrayList<>());

        OrderDTO orderDTO = new OrderDTO();

        UsersAddress usersAddress = new UsersAddress();
        usersAddress.setAddressName("name");

        List<AdditionalServices> additionalServices = new ArrayList<>();
        additionalServices.add(new AdditionalServices());

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