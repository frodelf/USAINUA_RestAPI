package com.avadamedia.USAINUA.controllers;

import com.avadamedia.USAINUA.entity.*;
import com.avadamedia.USAINUA.models.*;
import com.avadamedia.USAINUA.repositories.CreditCardsRepository;
import com.avadamedia.USAINUA.repositories.StorageRepository;
import com.avadamedia.USAINUA.repositories.UsersAddressRepository;
import com.avadamedia.USAINUA.repositories.UsersRepository;
import com.avadamedia.USAINUA.services.impl.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private UsersRepository usersRepository;
    @MockBean
    private StorageRepository storageRepository;
    @MockBean
    private CreditCardsRepository creditCardsRepository;
    @MockBean
    private UsersAddressRepository usersAddressRepository;

    @MockBean
    private UsersServiceImpl usersService;

    @MockBean
    private StorageServiceImpl storageService;

    @MockBean
    private CreditCardsServiceImpl creditCardsService;

    @MockBean
    private UsersAddressServiceImpl usersAddressService;

    @MockBean
    private FinancesServiceImpl financesService;
    @Test
    @WithMockUser("derkach.denis@lll.kpi.ua")
    public void testAddPersonalData() throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setName("some name");
        userDTO.setSurname("some surname");
        userDTO.setBirthday(new Date(1));
        userDTO.setPhone("0987654321");
        userDTO.setEmail("some_email@email.com");
        userDTO.setIsMan(true);

        User user = new User();

        when(usersService.getByEmail(anyString())).thenReturn(user);

        mockMvc.perform(post("/user/add-personal-data")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDTO)))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    public void testGetAllFinances() throws Exception {
        Finances finances1 = new Finances(new Date(1), 100);
        Finances finances2 = new Finances(new Date(2), 200);
        List<Finances> finances = Arrays.asList(finances1, finances2);
        User user = new User();
        user.setFinances(finances);
        when(usersService.getByEmail(any())).thenReturn(user);
        mockMvc.perform(get("/user/get-all-finances"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].sum").value(100))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].sum").value(200));
    }
    @Test
    @WithMockUser
    public void testGetMoney() throws Exception {
        User user = new User();
        user.setMoney(100);
        when(usersService.getByEmail(any())).thenReturn(user);
        mockMvc.perform(get("/user/get-money"))
                .andExpect(status().isOk())
                .andExpect(content().string("100.0"));
    }

    @Test
    @WithMockUser
    public void testAddMoney() throws Exception {
        double sum = 50.0;
        User user = new User();
        user.setMoney(100);
        when(usersService.getByEmail(any())).thenReturn(user);
        mockMvc.perform(put("/user/add-money")
                        .param("sum", "25.5"))
                .andExpect(status().isOk());
        Assert.assertEquals(125.5, user.getMoney(), 0.0);
    }

    @Test
    @WithMockUser
    public void testGetAllCreditCards() throws Exception {
        CreditCard creditCard1 = new CreditCard(1L, "1234123412341234", "12/12", "123");
        CreditCard creditCard2 = new CreditCard(2L, "224223422342234", "01/12", "134");
        User user = new User();
        user.setCreditCards(Arrays.asList(creditCard1, creditCard2));
        when(usersService.getByEmail(any())).thenReturn(user);
        mockMvc.perform(get("/user/get-credit-cards"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].number").value("1234123412341234"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].period").value("12/12"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].cvv").value("123"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].number").value("224223422342234"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].period").value("01/12"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].cvv").value("134"));
        ;
    }

    @Test
    @WithMockUser
    public void testAddCard() throws Exception {
        CreditCardDTO creditCardDTO = new CreditCardDTO();
        creditCardDTO.setNumber("0000999988887777");
        creditCardDTO.setPeriod("12/12");
        creditCardDTO.setCvv("121");
        User user = new User();
        user.setCreditCards(new ArrayList<>());
        when(usersService.getByEmail(any())).thenReturn(user);
        mockMvc.perform(post("/user/add-card")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(creditCardDTO)))
                .andExpect(status().isOk());

        Assert.assertEquals(1, user.getCreditCards().size());
    }

    @Test
    @WithMockUser
    public void testAddUsersAddress() throws Exception {
        UserAddressDTO userAddressDTO = new UserAddressDTO();
        userAddressDTO.setUsersName("some name");
        userAddressDTO.setUsersSurname("some surname");
        userAddressDTO.setCity("some city");
        userAddressDTO.setRegion("some region");
        userAddressDTO.setPhone("0987654321");
        userAddressDTO.setDepartment("%323");
        userAddressDTO.setAddressName("home");

        User user = new User();
        user.setUsersAddresses(new ArrayList<>());
        when(usersService.getByEmail(any())).thenReturn(user);

        mockMvc.perform(put("/user/add-users-address")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userAddressDTO)))
                .andExpect(status().isOk());

        Assert.assertEquals(1, user.getUsersAddresses().size());
    }

    @Test
    @WithMockUser
    public void testEditUsersAddress() throws Exception {
        Long addressId = 1L;
        UserAddressDTO addressDTO = new UserAddressDTO();
        addressDTO.setAddressName("home");
        addressDTO.setUsersName("user name");
        addressDTO.setUsersSurname("user surname");
        addressDTO.setRegion("some region");
        addressDTO.setPhone("0987654321");
        addressDTO.setCity("city");
        addressDTO.setDepartment("%543");


        mockMvc.perform(put("/user/edit-user-address/{id}", addressId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(addressDTO)))
                .andExpect(status().isOk());

    }

    @Test
    @WithMockUser
    public void testGetAllStorages() throws Exception {
        when(storageService.getAll()).thenReturn(Arrays.asList(new Storage(1L, "full name", "street", "city", "state", "index", "phone")));
        mockMvc.perform(get("/user/get-storages"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].fullName").value("full name"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].street").value("street"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].city").value("city"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].index").value("index"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].phone").value("phone"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].state").value("state"));
    }
}



//@SpringBootTest
//class UserControllerTest {
//
//    @Autowired
//    private UserController userController;
//
//    @MockBean
//    private UsersRepository usersRepository;
//    @MockBean
//    private StorageRepository storageRepository;
//    @MockBean
//    private CreditCardsRepository creditCardsRepository;
//    @MockBean
//    private UsersAddressRepository usersAddressRepository;
//    @Test
//    @WithMockUser(username = "derkach2007artem@gmail.com")
//    void addPersonalData() {
//        when(usersRepository.findByEmail(anyString())).thenReturn(Optional.of(new User()));
//        userController.addPersonalData(new UserDTO());
//        verify(usersRepository, times(1)).save(any());
//    }
//
//    @Test
//    @WithMockUser(username = "derkach2007artem@gmail.com")
//    void getAllFinances() {
//        List<Finances> finances = new ArrayList<>();
//        finances.add(new Finances(1L, new Date(2000, 12, 20),2100.25));
//        finances.add(new Finances(3L, new Date(2003, 1, 26),3210.5));
//
//        User user = new User();
//        user.setFinances(finances);
//
//        when(usersRepository.findByEmail(anyString())).thenReturn(Optional.of(user));
//
//        List<FinancesDTO> financesDTO = userController.getAllFinances();
//
//        assertEquals(2, financesDTO.size());
//        assertEquals(2100.25, financesDTO.get(0).getSum());
//        assertEquals(new Date(2000, 12, 20), financesDTO.get(0).getDate());
//        assertEquals(3210.5, financesDTO.get(1).getSum());
//        assertEquals(new Date(2003, 1, 26), financesDTO.get(1).getDate());
//    }
//
//    @Test
//    @WithMockUser(username = "derkach2007artem@gmail.com")
//    void getMoney() {
//        User user = new User();
//        user.setMoney(1000);
//        when(usersRepository.findByEmail(anyString())).thenReturn(Optional.of(user));
//        double money = userController.getMoney();
//        assertEquals(money, user.getMoney());
//    }
//
//    @Test
//    @WithMockUser(username = "derkach2007artem@gmail.com")
//    void addMoney() {
//        User user = new User();
//        user.setMoney(1000);
//        when(usersRepository.findByEmail(anyString())).thenReturn(Optional.of(user));
//        userController.addMoney(109);
//        assertEquals(1109, user.getMoney());
//    }
//
//    @Test
//    @WithMockUser(username = "derkach2007artem@gmail.com")
//    void getAllCreditCards() {
//        List<CreditCard> creditCards = new ArrayList<>();
//        creditCards.add(new CreditCard(1L, "0000111122223333", "12/12", "123"));
//        creditCards.add(new CreditCard(3L, "0011223344556677", "12/12", "234"));
//
//        User user = new User();
//        user.setCreditCards(creditCards);
//
//        when(usersRepository.findByEmail(anyString())).thenReturn(Optional.of(user));
//
//        List<CreditCardDTO> creditCardDTO = userController.getAllCreditCards();
//
//        assertEquals(2, creditCardDTO.size());
//        assertEquals("0000111122223333", creditCardDTO.get(0).getNumber());
//        assertEquals("12/12", creditCardDTO.get(0).getPeriod());
//        assertEquals("123", creditCardDTO.get(0).getCvv());
//        assertEquals("0011223344556677", creditCardDTO.get(1).getNumber());
//        assertEquals("12/12", creditCardDTO.get(1).getPeriod());
//        assertEquals("234", creditCardDTO.get(1).getCvv());
//    }
//
//    @Test
//    @WithMockUser(username = "derkach2007artem@gmail.com")
//    void addCard() {
//        User user = new User();
//        user.setCreditCards(new ArrayList<CreditCard>());
//        when(usersRepository.findByEmail(anyString())).thenReturn(Optional.of(user));
//        userController.addCard(new CreditCardDTO("0099887766554433", "11/11", "567"));
//        List<CreditCard> cards = user.getCreditCards();
//        assertEquals(1, cards.size());
//        assertEquals("0099887766554433", cards.get(0).getCardsNumber());
//        assertEquals("11/11", cards.get(0).getValidityPeriod());
//        assertEquals("567", cards.get(0).getCvv());
//    }
//
//    @Test
//    @WithMockUser(username = "derkach2007artem@gmail.com")
//    void addUsersAddress() {
//        User user = new User();
//        user.setUsersAddresses(new ArrayList<UsersAddress>());
//        UserAddressDTO usersAddress = new UserAddressDTO();
//        usersAddress.setAddressName("home");
//        when(usersRepository.findByEmail(anyString())).thenReturn(Optional.of(user));
//        userController.addUsersAddress(usersAddress);
//        List<UsersAddress> addresses = user.getUsersAddresses();
//        assertEquals(1, addresses.size());
//        assertEquals("home", addresses.get(0).getAddressName());
//    }
//
//    @Test
//    @WithMockUser(username = "derkach2007artem@gmail.com")
//    void getAllStorages() {
//        Storage storage1 = new Storage();
//        Storage storage2 = new Storage();
//        storage1.setPhone("1234567890");
//        storage2.setPhone("0987654321");
//        List<Storage> storages = new ArrayList<>();
//        storages.add(storage1);
//        storages.add(storage2);
//
//        when(storageRepository.findAll()).thenReturn(storages);
//        List<StorageDTO> storageDTO = userController.getAllStorages();
//
//        assertEquals(2, storageDTO.size());
//        assertEquals("1234567890", storageDTO.get(0).getPhone());
//        assertEquals("0987654321", storageDTO.get(1).getPhone());
//    }
//
//    @Test
//    void editUsersAddress() {
//        UserAddressDTO userAddressDTO = new UserAddressDTO();
//        userAddressDTO.setAddressName("home");
//        userAddressDTO.setUsersName("edit");
//        userAddressDTO.setUsersSurname("edit");
//        userAddressDTO.setCity("City");
//        userAddressDTO.setPhone("098789087");
//        userAddressDTO.setDepartment("qwerty");
//        userAddressDTO.setRegion("weqr");
//
//        userController.editUsersAddress(1L, userAddressDTO);
//
//        verify(usersAddressRepository, times(1)).save(any());
//    }
//}