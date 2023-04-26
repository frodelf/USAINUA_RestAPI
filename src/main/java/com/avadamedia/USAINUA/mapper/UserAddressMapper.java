package com.avadamedia.USAINUA.mapper;

import com.avadamedia.USAINUA.entity.UsersAddress;
import com.avadamedia.USAINUA.models.UserAddressDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserAddressMapper {
    UserAddressDTO toDto(UsersAddress usersAddress);
    List<UserAddressDTO> toDtoList(List<UsersAddress> usersAddresses);
    UsersAddress toEntity(UserAddressDTO userAddressDTO);
    List<UsersAddress> toEntityList(List<UserAddressDTO> userAddressDTO);
}
