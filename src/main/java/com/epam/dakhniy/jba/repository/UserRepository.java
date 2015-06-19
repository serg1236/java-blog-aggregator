package com.epam.dakhniy.jba.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epam.dakhniy.jba.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
