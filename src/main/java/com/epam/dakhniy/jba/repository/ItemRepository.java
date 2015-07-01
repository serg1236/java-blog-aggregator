package com.epam.dakhniy.jba.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.epam.dakhniy.jba.entity.Item;
import com.epam.dakhniy.jba.entity.Blog;

public interface ItemRepository extends JpaRepository<Item, Integer> {
	
	 List<Item> findByBlog(Blog blog, Pageable pageable);

}
