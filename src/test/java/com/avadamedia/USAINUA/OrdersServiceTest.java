package com.avadamedia.USAINUA;

import com.avadamedia.USAINUA.entity.AdditionalServices;
import com.avadamedia.USAINUA.entity.Orders;
import com.avadamedia.USAINUA.entity.Users;
import com.avadamedia.USAINUA.entity.UsersAddress;
import com.avadamedia.USAINUA.enums.Status;
import com.avadamedia.USAINUA.models.OrderDTO;
import com.avadamedia.USAINUA.repositories.*;
import com.avadamedia.USAINUA.services.impl.AdditionalServicesServiceImpl;
import com.avadamedia.USAINUA.services.impl.OrdersServiceImpl;
import com.avadamedia.USAINUA.services.impl.UsersAddressServiceImpl;
import com.avadamedia.USAINUA.services.impl.UsersServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class OrdersServiceTest {
    @Autowired
    private OrdersServiceImpl ordersService;
    @Autowired
    private UsersServiceImpl usersService;
    @Autowired
    private AdditionalServicesServiceImpl additionalServicesService;
    @MockBean
    private OrdersRepository ordersRepository;
    @MockBean
    private UsersRepository usersRepository;
    @MockBean
    private UsersAddressRepository usersAddressRepository;
    @MockBean
    private FinancesRepository financesRepository;
    @MockBean
    private AdditionalServicesRepository additionalServicesRepository;
    @Test
    @WithMockUser(username = "derkach2007artem@gmail.com")
    void testPayOrderSuccess() {
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
    void testAddOrder() {
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