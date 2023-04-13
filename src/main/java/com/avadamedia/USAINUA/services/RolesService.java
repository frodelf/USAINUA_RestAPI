package com.avadamedia.USAINUA.services;

import com.avadamedia.USAINUA.models.Roles;
import com.avadamedia.USAINUA.repositories.RolesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RolesService {
    private final RolesRepository rolesRepository;

    public Roles getById(long id){
        return rolesRepository.findById(id).get();
    }
}
