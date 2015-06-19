package com.epam.dakhniy.jba.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epam.dakhniy.jba.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Integer> {

}
