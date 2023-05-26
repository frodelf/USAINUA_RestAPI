package com.avadamedia.USAINUA.controllers;

import com.avadamedia.USAINUA.entity.*;
import com.avadamedia.USAINUA.models.*;
import com.avadamedia.USAINUA.repositories.CreditCardsRepository;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserControllerTest {

    @Autowired
    private UserController userController;

    @MockBean
    private UsersRepository usersRepository;
    @MockBean
    private StorageRepository storageRepository;
    @MockBean
    private CreditCardsRepository creditCardsRepository;
    @MockBean
    private UsersAddressRepository usersAddressRepository;
    @Test
    @WithMockUser(username = "derkach2007artem@gmail.com")
    void addPersonalData() {
        when(usersRepository.findByEmail(anyString())).thenReturn(Optional.of(new User()));
        userController.addPersonalData(new UserDTO());
        verify(usersRepository, times(1)).save(any());
    }

    @Test
    @WithMockUser(username = "derkach2007artem@gmail.com")
    void getAllFinances() {
        List<Finances> finances = new ArrayList<>();
        finances.add(new Finances(1L, new Date(2000, 12, 20),2100.25));
        finances.add(new Finances(3L, new Date(2003, 1, 26),3210.5));

        User user = new User();
        user.setFinances(finances);

        when(usersRepository.findByEmail(anyString())).thenReturn(Optional.of(user));

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
        User user = new User();
        user.setMoney(1000);
        when(usersRepository.findByEmail(anyString())).thenReturn(Optional.of(user));
        double money = userController.getMoney();
        assertEquals(money, user.getMoney());
    }

    @Test
    @WithMockUser(username = "derkach2007artem@gmail.com")
    void addMoney() {
        User user = new User();
        user.setMoney(1000);
        when(usersRepository.findByEmail(anyString())).thenReturn(Optional.of(user));
        userController.addMoney(109);
        assertEquals(1109, user.getMoney());
    }

    @Test
    @WithMockUser(username = "derkach2007artem@gmail.com")
    void getAllCreditCards() {
        List<CreditCard> creditCards = new ArrayList<>();
        creditCards.add(new CreditCard(1L, "0000111122223333", "12/12", "123"));
        creditCards.add(new CreditCard(3L, "0011223344556677", "12/12", "234"));

        User user = new User();
        user.setCreditCards(creditCards);

        when(usersRepository.findByEmail(anyString())).thenReturn(Optional.of(user));

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
        User user = new User();
        user.setCreditCards(new ArrayList<CreditCard>());
        when(usersRepository.findByEmail(anyString())).thenReturn(Optional.of(user));
        userController.addCard(new CreditCardDTO("0099887766554433", "11/11", "567"));
        List<CreditCard> cards = user.getCreditCards();
        assertEquals(1, cards.size());
        assertEquals("0099887766554433", cards.get(0).getCardsNumber());
        assertEquals("11/11", cards.get(0).getValidityPeriod());
        assertEquals("567", cards.get(0).getCvv());
    }

    @Test
    @WithMockUser(username = "derkach2007artem@gmail.com")
    void addUsersAddress() {
        User user = new User();
        user.setUsersAddresses(new ArrayList<UsersAddress>());
        UserAddressDTO usersAddress = new UserAddressDTO();
        usersAddress.setAddressName("home");
        when(usersRepository.findByEmail(anyString())).thenReturn(Optional.of(user));
        userController.addUsersAddress(usersAddress);
        List<UsersAddress> addresses = user.getUsersAddresses();
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

        userController.editUsersAddress(1L, userAddressDTO);

        verify(usersAddressRepository, times(1)).save(any());
    }
}