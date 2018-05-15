package com.samsun.samsunvina.services;

import com.samsun.samsunvina.entities.Role;
import com.samsun.samsunvina.repositories.RoleRepository;
import com.samsun.samsunvina.services.interfaces.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role findByName(Enum name) {
        return roleRepository.findByName(name);
    }
}
