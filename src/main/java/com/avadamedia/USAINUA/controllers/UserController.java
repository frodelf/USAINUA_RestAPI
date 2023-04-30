package com.avadamedia.USAINUA.controllers;

import com.avadamedia.USAINUA.entity.*;
import com.avadamedia.USAINUA.mapper.*;
import com.avadamedia.USAINUA.models.*;
import com.avadamedia.USAINUA.services.impl.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/user/")
@RequiredArgsConstructor
@Tag(name = "User Controller", description = "User API")
public class UserController {
    private final UsersServiceImpl usersService;
    private final StorageServiceImpl storageService;
    private final CreditCardsServiceImpl creditCardsService;
    private final UsersAddressServiceImpl usersAddressService;
    private final FinancesServiceImpl financesService;
    private final CreditCardMapper creditCardMapper;
    private final UserAddressMapper userAddressMapper;
    private final StorageMapper storageMapper;
    private final FinancesMapper financesMapping;

    @Operation(summary = "Update the personal data by user")
    @PostMapping("personal-data")
    public void addPersonalData(@RequestBody @Valid UserDTO userDTO){
        UsersMapper usersMapper = new UsersMapper(usersService);
        usersService.save(usersMapper.toEntity(userDTO));
    }
    @Operation(summary = "Get all the finances by user")
    @GetMapping("finances")
    public List<FinancesDTO> getAllFinances(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return financesMapping.toDtoList(usersService.getByEmail(authentication.getName()).getFinances());
    }
    @GetMapping("get-money")
    @Operation(summary = "Get the money by user")
    public double getMoney(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return usersService.getByEmail(authentication.getName()).getMoney();
    }
    @Operation(summary = "Update the money by user")
    @PostMapping("add-money")
    public void addMoney(@Parameter(description = "The money to be deducted from the user's balance") @RequestParam("sum")double sum){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Users users = usersService.getByEmail(authentication.getName());
        users.setMoney(users.getMoney()+sum);
        usersService.save(users);
    }
    @Operation(summary = "Get the credit card by user")
    @GetMapping("get-credit-cards")
    public List<CreditCardDTO> getAllCreditCards(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return creditCardMapper.toDtoList(usersService.getByEmail(authentication.getName()).getCreditCards());
    }
    @Operation(summary = "Add the credit card for user")
    @PostMapping("add-card")
    public void addCard(@RequestBody @Valid CreditCardDTO creditCardDTO){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Users users = usersService.getByEmail(authentication.getName());
        CreditCards cards = creditCardMapper.toEntity(creditCardDTO);
        creditCardsService.save(cards);
        users.getCreditCards().add(cards);
        usersService.save(users);
    }
    @Operation(summary = "Add the address for user")
    @PostMapping("add-users-address")
    public void addUsersAddress(@RequestBody @Valid UserAddressDTO userAddressDTO){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Users users = usersService.getByEmail(authentication.getName());
        UsersAddress address = userAddressMapper.toEntity(userAddressDTO);
        usersAddressService.save(address);
        users.getUsersAddresses().add(address);
        usersService.save(users);
    }
    @Operation(summary = "Get storages")
    @GetMapping("get-storages")
    public List<StorageDTO> getAllStorages(){
        return storageMapper.toDtoList(storageService.getAll());
    }
}