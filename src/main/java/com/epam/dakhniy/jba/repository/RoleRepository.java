package com.epam.dakhniy.jba.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epam.dakhniy.jba.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

	Role findByName(String string);

}
