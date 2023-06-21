package com.avadamedia.USAINUA.controllers;

import com.avadamedia.USAINUA.entity.Order;
import com.avadamedia.USAINUA.entity.User;
import com.avadamedia.USAINUA.models.OrderDTO;
import com.avadamedia.USAINUA.repositories.UsersRepository;
import com.avadamedia.USAINUA.services.impl.OrdersServiceImpl;
import com.avadamedia.USAINUA.services.impl.UsersServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private OrdersServiceImpl ordersService;

    @MockBean
    private UsersServiceImpl usersService;
    @MockBean
    private UsersRepository usersRepository;

    @Test
    @WithMockUser
    public void testGetAllOrders() throws Exception {
        Order order = new Order();
        order.setTransport("plane");
        order.setDescription("some description");
        order.setLink("http://qwerty");
        order.setTrackNumber("00992211");
        order.setPrice(120.5);
        order.setWeight(12.0);
        order.setNumber(3);
        order.setOnlyDelivery(true);

        User user = new User();
        user.setEmail("some.email@gmail.com");
        user.setOrders(Arrays.asList(order));

        Mockito.when(usersService.getByEmail(Mockito.anyString())).thenReturn(user);

        mockMvc.perform(MockMvcRequestBuilders.get("/orders/get-all-orders/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].transport").value("plane"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].description").value("some description"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].link").value("http://qwerty"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].trackNumber").value("00992211"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(1));
    }

    @Test
    @WithMockUser
    public void testGetOrderById() throws Exception {
        Order order = new Order();
        order.setTransport("plane");
        order.setDescription("some description");
        order.setLink("http://qwerty");
        order.setTrackNumber("00992211");
        order.setPrice(120.5);
        order.setWeight(12.0);
        order.setNumber(3);
        order.setOnlyDelivery(true);

        Mockito.when(ordersService.getById(Mockito.anyLong())).thenReturn(order);

        mockMvc.perform(MockMvcRequestBuilders.get("/orders/order/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.transport").value("plane"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("some description"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.link").value("http://qwerty"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.trackNumber").value("00992211"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(120.5))
                .andExpect(MockMvcResultMatchers.jsonPath("$.weight").value(12.0))
                .andExpect(MockMvcResultMatchers.jsonPath("$.number").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath("$.onlyDelivery").value(true));
    }


    @Test
    @WithMockUser
    public void testPayOrder() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/orders/pay-order/{id}", 1))
            .andExpect(MockMvcResultMatchers.status().isOk());

            verify(ordersService, Mockito.times(1)).payOrder(Mockito.anyLong());
    }

    @Test
    @WithMockUser
    public void testAddOrder() throws Exception {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setWeight(10.50);
        orderDTO.setTransport("plane");
        orderDTO.setNumber(1);


        List<Long> idAdditionalServices = Collections.singletonList(1L);
        Long idAddress = 1L;

        String requestBody = new ObjectMapper().writeValueAsString(orderDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/orders/make-order")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody)
                        .param("additional-services", idAdditionalServices.get(0).toString())
                        .param("id-address", idAddress.toString()))
                .andExpect(MockMvcResultMatchers.status().isOk());


    }
}