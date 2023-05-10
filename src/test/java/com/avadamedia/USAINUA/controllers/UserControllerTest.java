package com.avadamedia.USAINUA.controllers;

import com.avadamedia.USAINUA.entity.*;
import com.avadamedia.USAINUA.models.*;
import com.avadamedia.USAINUA.repositories.StorageRepository;
import com.avadamedia.USAINUA.repositories.UsersAddressRepository;
import com.avadamedia.USAINUA.repositories.UsersRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class UserControllerTest {

    @Autowired
    private UserController userController;

    @MockBean
    private UsersRepository usersRepository;
    @MockBean
    private StorageRepository storageRepository;
    @Test
    @WithMockUser(username = "derkach2007artem@gmail.com")
    void addPersonalData() {
        when(usersRepository.findByEmail(anyString())).thenReturn(Optional.of(new Users()));
        userController.addPersonalData(new UserDTO());
        verify(usersRepository, times(1)).save(any());
    }

    @Test
    @WithMockUser(username = "derkach2007artem@gmail.com")
    void getAllFinances() {
        List<Finances> finances = new ArrayList<>();
        finances.add(new Finances(1L, new Date(2000, 12, 20),2100.25));
        finances.add(new Finances(3L, new Date(2003, 1, 26),3210.5));

        Users users = new Users();
        users.setFinances(finances);

        when(usersRepository.findByEmail(anyString())).thenReturn(Optional.of(users));

        List<FinancesDTO> financesDTO = userController.getAllFinances();

        assertEquals(2, financesDTO.size());
        assertEquals(2100.25, financesDTO.get(0).getSum());
        assertEquals(new Date(2000, 12, 20), financesDTO.get(0).getDate());
        assertEquals(3210.5, financesDTO.get(1).getSum());
        assertEquals(new Date(2003, 1, 26), financesDTO.get(1).getDate());
    }

    @Test
    @WithMockUser(username = "derkach2007artem@gmail.com")
    void getMoney() {
        Users users = new Users();
        users.setMoney(1000);
        when(usersRepository.findByEmail(anyString())).thenReturn(Optional.of(users));
        double money = userController.getMoney();
        assertEquals(money, users.getMoney());
    }

    @Test
    @WithMockUser(username = "derkach2007artem@gmail.com")
    void addMoney() {
        Users users = new Users();
        users.setMoney(1000);
        when(usersRepository.findByEmail(anyString())).thenReturn(Optional.of(users));
        userController.addMoney(109);
        assertEquals(1109, users.getMoney());
    }

    @Test
    @WithMockUser(username = "derkach2007artem@gmail.com")
    void getAllCreditCards() {
        List<CreditCards> creditCards = new ArrayList<>();
        creditCards.add(new CreditCards(1L, "0000111122223333", "12/12", "123"));
        creditCards.add(new CreditCards(3L, "0011223344556677", "12/12", "234"));

        Users users = new Users();
        users.setCreditCards(creditCards);

        when(usersRepository.findByEmail(anyString())).thenReturn(Optional.of(users));

        List<CreditCardDTO> creditCardDTO = userController.getAllCreditCards();

        assertEquals(2, creditCardDTO.size());
        assertEquals("0000111122223333", creditCardDTO.get(0).getNumber());
        assertEquals("12/12", creditCardDTO.get(0).getPeriod());
        assertEquals("123", creditCardDTO.get(0).getCvv());
        assertEquals("0011223344556677", creditCardDTO.get(1).getNumber());
        assertEquals("12/12", creditCardDTO.get(1).getPeriod());
        assertEquals("234", creditCardDTO.get(1).getCvv());
    }

    @Test
    @WithMockUser(username = "derkach2007artem@gmail.com")
    void addCard() {
        Users users = new Users();
        users.setCreditCards(new ArrayList<CreditCards>());
        when(usersRepository.findByEmail(anyString())).thenReturn(Optional.of(users));
        userController.addCard(new CreditCardDTO("0099887766554433", "11/11", "567"));
        List<CreditCards> cards = users.getCreditCards();
        assertEquals(1, cards.size());
        assertEquals("0099887766554433", cards.get(0).getCardsNumber());
        assertEquals("11/11", cards.get(0).getValidityPeriod());
        assertEquals("567", cards.get(0).getCvv());
    }

    @Test
    @WithMockUser(username = "derkach2007artem@gmail.com")
    void addUsersAddress() {
        Users users = new Users();
        users.setUsersAddresses(new ArrayList<UsersAddress>());
        UserAddressDTO usersAddress = new UserAddressDTO();
        usersAddress.setAddressName("home");
        when(usersRepository.findByEmail(anyString())).thenReturn(Optional.of(users));
        userController.addUsersAddress(usersAddress);
        List<UsersAddress> addresses = users.getUsersAddresses();
        assertEquals(1, addresses.size());
        assertEquals("home", addresses.get(0).getAddressName());
    }

    @Test
    @WithMockUser(username = "derkach2007artem@gmail.com")
    void getAllStorages() {
        Storage storage1 = new Storage();
        Storage storage2 = new Storage();
        storage1.setPhone("1234567890");
        storage2.setPhone("0987654321");
        List<Storage> storages = new ArrayList<>();
        storages.add(storage1);
        storages.add(storage2);

        when(storageRepository.findAll()).thenReturn(storages);
        List<StorageDTO> storageDTO = userController.getAllStorages();

        assertEquals(2, storageDTO.size());
        assertEquals("1234567890", storageDTO.get(0).getPhone());
        assertEquals("0987654321", storageDTO.get(1).getPhone());
    }
}