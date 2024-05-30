package com.example.issuemanagement.service;

import com.example.issuemanagement.model.Role;
import com.example.issuemanagement.model.ERole;
import com.example.issuemanagement.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Optional<Role> findByName(ERole name) {
        return roleRepository.findByName(name);
    }
}
