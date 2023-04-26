package com.avadamedia.USAINUA.mapper;

import com.avadamedia.USAINUA.entity.Users;
import com.avadamedia.USAINUA.models.UserDTO;
import com.avadamedia.USAINUA.services.impl.UsersServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@RequiredArgsConstructor
public class UsersMapper {
    private final UsersServiceImpl usersService;

    public Users toEntity(UserDTO userDTO){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Users user = usersService.getByEmail(authentication.getName());

        user.setName(userDTO.getName());
        user.setSurname(userDTO.getSurname());
        user.setBirthday(userDTO.getDate());
        user.setIsMan(userDTO.getIsMan());
        user.setPhone(userDTO.getPhone());
        user.setEmail(userDTO.getEmail());

        return user;
    }
}
