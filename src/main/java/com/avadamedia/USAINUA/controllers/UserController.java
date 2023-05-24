package com.avadamedia.USAINUA.controllers;

import com.avadamedia.USAINUA.entity.*;
import com.avadamedia.USAINUA.mapper.*;
import com.avadamedia.USAINUA.models.*;
import com.avadamedia.USAINUA.services.impl.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
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
@SecurityRequirement(name = "bearerAuth")
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

    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
    })
    @Operation(summary = "Update the personal data by user")
    @PostMapping("add-personal-data")
    public void addPersonalData(@RequestBody @Valid UserDTO userDTO){
        UsersMapper usersMapper = new UsersMapper(usersService);
        usersService.save(usersMapper.toEntity(userDTO));
    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
    })
    @Operation(summary = "Get all the finances by user")
    @GetMapping("get-all-finances")
    public List<FinancesDTO> getAllFinances(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return financesMapping.toDtoList(usersService.getByEmail(authentication.getName()).getFinances());
    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
    })
    @GetMapping("get-money")
    @Operation(summary = "Get the money by user")
    public double getMoney(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return usersService.getByEmail(authentication.getName()).getMoney();
    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
    })
    @Operation(summary = "Update the money by user")
    @PutMapping("add-money")
    public void addMoney(
            @Parameter(description = "Money that will be added to the user's balance")
            @RequestParam("sum")double sum){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = usersService.getByEmail(authentication.getName());
        user.setMoney(user.getMoney()+sum);
        usersService.save(user);
    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
    })
    @Operation(summary = "Get all the user's credit cards")
    @GetMapping("get-credit-cards")
    public List<CreditCardDTO> getAllCreditCards(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return creditCardMapper.toDtoList(usersService.getByEmail(authentication.getName()).getCreditCards());
    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
    })
    @Operation(summary = "Add the credit card for user")
    @PostMapping("add-card")
    public void addCard(@RequestBody @Valid CreditCardDTO creditCardDTO){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = usersService.getByEmail(authentication.getName());
        CreditCard cards = creditCardMapper.toEntity(creditCardDTO);
        creditCardsService.save(cards);
        user.getCreditCards().add(cards);
        usersService.save(user);
    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
    })
    @Operation(summary = "Add the address for user")
    @PutMapping("add-users-address")
    public void addUsersAddress(@RequestBody @Valid UserAddressDTO userAddressDTO){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = usersService.getByEmail(authentication.getName());
        UsersAddress address = userAddressMapper.toEntity(userAddressDTO);
        usersAddressService.save(address);
        user.getUsersAddresses().add(address);
        usersService.save(user);
    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
    })
    @Operation(summary = "Get storages")
    @GetMapping("get-storages")
    public List<StorageDTO> getAllStorages(){
        return storageMapper.toDtoList(storageService.getAll());
    }
}