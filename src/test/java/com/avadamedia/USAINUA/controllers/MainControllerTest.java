package com.avadamedia.USAINUA.controllers;

import com.avadamedia.USAINUA.UsainuaApplication;
import com.avadamedia.USAINUA.entity.AdditionalService;
import com.avadamedia.USAINUA.entity.Product;
import com.avadamedia.USAINUA.entity.Shop;
import com.avadamedia.USAINUA.models.ProductDTO;
import com.avadamedia.USAINUA.models.ShopDTO;
import com.avadamedia.USAINUA.repositories.ProductsRepository;
import com.avadamedia.USAINUA.repositories.ShopsRepository;
import com.avadamedia.USAINUA.repositories.UsersRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class MainControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private UsersRepository usersRepository;

    @Test
    @WithMockUser
    public void testPurchaseAndDeliveryApproximatePrice() throws Exception {
        List<AdditionalService> additionalServices = Arrays.asList(
                new AdditionalService(1L,"Service 1", 10.0),
                new AdditionalService(2L, "Service 2", 15.0)
        );

        String additionalServicesJson = objectMapper.writeValueAsString(additionalServices);

        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/approximate-price/purchase-and-delivery/")
                        .param("transport", "plane")
                        .content(objectMapper.writeValueAsString(additionalServices))
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("weight", "10")
                        .param("price", "1200"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("2230.0"));
    }

    @Test
    @WithMockUser
    public void testDeliveryApproximatePrice() throws Exception {
        List<AdditionalService> additionalServices = Arrays.asList(
                new AdditionalService(1L, "Service 1", 10.0),
                new AdditionalService(2L, "Service 2", 15.0)
        );

        mockMvc.perform(MockMvcRequestBuilders.post("/approximate-price/delivery/")
                        .param("transport", "ship")
                        .content(objectMapper.writeValueAsString(additionalServices))
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("weight", "20"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("531.0"));
    }
}

//
//@SpringBootTest
//class MainControllerTest {
//
//    @Autowired
//    private MainController mainController;
//    @Autowired
//    private ShopController shopController;
//    @Autowired
//    private ProductController productController;
//    @MockBean
//    private ProductsRepository productsRepository;
//    @MockBean
//    private ShopsRepository shopsRepository;
//    @Test
//    void purchaseAndDeliveryApproximatePrice() {
//        Assertions.assertEquals(1085, mainController.purchaseAndDeliveryApproximatePrice("plane", new ArrayList<AdditionalService>(), 100, 350));
//        Assertions.assertEquals(559.5, mainController.purchaseAndDeliveryApproximatePrice("ship", new ArrayList<AdditionalService>(Arrays.asList(new AdditionalService(1L, "asdf", 12))), 100, 350));
//        Assertions.assertEquals(800, mainController.purchaseAndDeliveryApproximatePrice("another", new ArrayList<AdditionalService>(), 100, 350));
//    }
//
//    @Test
//    void deliveryApproximatePrice() {
//        Assertions.assertEquals(1050, mainController.deliveryApproximatePrice("plane", new ArrayList<AdditionalService>(), 100));
//        Assertions.assertEquals(542, mainController.deliveryApproximatePrice("ship", new ArrayList<AdditionalService>(Arrays.asList(new AdditionalService(1L, "asdf", 12))), 100));
//        Assertions.assertEquals(800, mainController.deliveryApproximatePrice("another", new ArrayList<AdditionalService>(), 100));
//    }
//
//    @Test
//    void getAllProducts() {
//        long id = 1L;
//        List<Product> products = Arrays.asList(
//                new Product(1L,"ball1", 10, "Sport", "http://asdf", "asdgfqweffwedfgs&df"),
//                new Product(2L,"ball2", 11, "Another", "http://asdf", "dsdfgsxfhdhgfgh")
//                );
//        Page<Product> productsPage = new PageImpl<>(products, PageRequest.of(0, 2), products.size());
//
//        when(productsRepository.findAll(PageRequest.of((int)(id-1), 2))).thenReturn(productsPage);
//
//        List<ProductDTO> productDTOList = productController.getAllProducts(id);
//
//        assertEquals(2, productDTOList.size());
//        assertEquals("ball1", productDTOList.get(0).getName());
//        assertEquals(10, productDTOList.get(0).getPrice());
//        assertEquals("Sport", productDTOList.get(0).getType());
//        assertEquals("http://asdf", productDTOList.get(0).getLink());
//        assertEquals("asdgfqweffwedfgs&df", productDTOList.get(0).getImageName());
//        assertEquals("ball2", productDTOList.get(1).getName());
//        assertEquals(11, productDTOList.get(1).getPrice());
//        assertEquals("Another", productDTOList.get(1).getType());
//        assertEquals("http://asdf", productDTOList.get(1).getLink());
//        assertEquals("dsdfgsxfhdhgfgh", productDTOList.get(1).getImageName());
//    }
//    @Test
//    void getAllShops() {
//        long id = 1L;
//        List<Shop> shops = Arrays.asList(
//                new Shop(1L, "Shop1", "image1.png"),
//                new Shop(2L, "Shop2", "image2.png")
//        );
//        Page<Shop> shopsPage = new PageImpl<>(shops, PageRequest.of(0, 2), shops.size());
//
//        when(shopsRepository.findAll(PageRequest.of((int)(id-1), 2))).thenReturn(shopsPage);
//
//        List<ShopDTO> shopDTOList = shopController.getAllShops(id);
//
//        assertEquals(2, shopDTOList.size());
//        assertEquals("Shop1", shopDTOList.get(0).getLink());
//        assertEquals("image1.png", shopDTOList.get(0).getImageName());
//        assertEquals("Shop2", shopDTOList.get(1).getLink());
//        assertEquals("image2.png", shopDTOList.get(1).getImageName());
//    }
//
//}