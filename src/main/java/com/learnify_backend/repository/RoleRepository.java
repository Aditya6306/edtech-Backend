package com.learnify_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learnify_backend.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
    public Role findByRoleName(String roleName);
}
