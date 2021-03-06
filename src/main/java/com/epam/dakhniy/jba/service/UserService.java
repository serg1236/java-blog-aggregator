package com.epam.dakhniy.jba.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.epam.dakhniy.jba.entity.Blog;
import com.epam.dakhniy.jba.entity.Item;
import com.epam.dakhniy.jba.entity.Role;
import com.epam.dakhniy.jba.entity.User;
import com.epam.dakhniy.jba.repository.BlogRepository;
import com.epam.dakhniy.jba.repository.ItemRepository;
import com.epam.dakhniy.jba.repository.RoleRepository;
import com.epam.dakhniy.jba.repository.UserRepository;


@Service
@Transactional
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BlogRepository blogRepository;
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	public List<User> findAll(){
		return userRepository.findAll();
	}

	public User findOne(int id) {
		return userRepository.findOne(id);
	}

	@Transactional
	public User findOneWithBlogs(int id) {
		User user = findOne(id);
		List<Blog> blogs = blogRepository.findByUser(user);
		
		for (Blog blog : blogs) {
			List<Item> items = itemRepository.findByBlog(blog, new PageRequest(0, 10, Direction.DESC, "publishedDate"));
			blog.setItems(items);
		}
		user.setBlogs(blogs);
		return user;
	}

	public void save(User user) {
		user.setEnabled(true);
		Role userRole = roleRepository.findByName("ROLE_USER");
		List<Role> roles = new ArrayList<Role>();
		roles.add(userRole);
		user.setRoles(roles);
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword()));
		userRepository.save(user);
		
	}
}
