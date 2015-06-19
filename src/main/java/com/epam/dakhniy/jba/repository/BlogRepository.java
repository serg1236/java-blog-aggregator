package com.epam.dakhniy.jba.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epam.dakhniy.jba.entity.Blog;

public interface BlogRepository extends JpaRepository<Blog, Integer> {

}
