package com.avadamedia.USAINUA.mapper;

import com.avadamedia.USAINUA.entity.UsersAddress;
import com.avadamedia.USAINUA.models.UserAddressDTO;

import java.util.ArrayList;
import java.util.List;

public class UserAddressMapper {
    public UserAddressDTO toDto(UsersAddress usersAddress) {
        if ( usersAddress == null ) {
            return null;
        }

        UserAddressDTO userAddressDTO = new UserAddressDTO();

        userAddressDTO.setAddressName( usersAddress.getAddressName() );
        userAddressDTO.setUsersName( usersAddress.getUsersName() );
        userAddressDTO.setUsersSurname( usersAddress.getUsersSurname() );
        userAddressDTO.setPhone( usersAddress.getPhone() );
        userAddressDTO.setRegion( usersAddress.getRegion() );
        userAddressDTO.setCity( usersAddress.getCity() );
        userAddressDTO.setDepartment( usersAddress.getDepartment() );

        return userAddressDTO;
    }
    public List<UserAddressDTO> toDtoList(List<UsersAddress> usersAddresses) {
        if ( usersAddresses == null ) {
            return null;
        }

        List<UserAddressDTO> list = new ArrayList<UserAddressDTO>( usersAddresses.size() );
        for ( UsersAddress usersAddress : usersAddresses ) {
            list.add( toDto( usersAddress ) );
        }

        return list;
    }
    public UsersAddress toEntity(UserAddressDTO userAddressDTO) {
        if ( userAddressDTO == null ) {
            return null;
        }

        UsersAddress usersAddress = new UsersAddress();

        usersAddress.setAddressName( userAddressDTO.getAddressName() );
        usersAddress.setUsersName( userAddressDTO.getUsersName() );
        usersAddress.setUsersSurname( userAddressDTO.getUsersSurname() );
        usersAddress.setPhone( userAddressDTO.getPhone() );
        usersAddress.setRegion( userAddressDTO.getRegion() );
        usersAddress.setCity( userAddressDTO.getCity() );
        usersAddress.setDepartment( userAddressDTO.getDepartment() );

        return usersAddress;
    }
    public List<UsersAddress> toEntityList(List<UserAddressDTO> userAddressDTO) {
        if ( userAddressDTO == null ) {
            return null;
        }

        List<UsersAddress> list = new ArrayList<UsersAddress>( userAddressDTO.size() );
        for ( UserAddressDTO userAddressDTO1 : userAddressDTO ) {
            list.add( toEntity( userAddressDTO1 ) );
        }

        return list;
    }
}