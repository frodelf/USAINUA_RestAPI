package com.avadamedia.USAINUA.services.impl;

import com.avadamedia.USAINUA.entity.Role;
import com.avadamedia.USAINUA.repositories.RolesRepository;
import com.avadamedia.USAINUA.services.RolesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RolesServiceImpl implements RolesService {
    private final RolesRepository rolesRepository;

    public Role getById(long id){
        return rolesRepository.findById(id).get();
    }
    public void save(Role role){rolesRepository.save(role);}

}
