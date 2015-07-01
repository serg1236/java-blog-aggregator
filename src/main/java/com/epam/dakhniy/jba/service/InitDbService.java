package com.epam.dakhniy.jba.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
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
public class InitDbService {

	@Autowired
	private BlogRepository blogRepository;
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	
	@PostConstruct
	public void init(){
		Role userRole = new Role();
		userRole.setName("ROLE_USER");
		roleRepository.save(userRole);
		
		Role adminRole = new Role();
		adminRole.setName("ROLE_ADMIN");
		roleRepository.save(adminRole);
		
		User adminUser = new User();
		adminUser.setName("admin");
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		adminUser.setPassword(encoder.encode("admin"));
		List<Role> roles = new ArrayList<Role>();
		roles.add(adminRole);
		roles.add(userRole);
		adminUser.setRoles(roles);
		adminUser.setEnabled(true);
		userRepository.save(adminUser);
		
		Blog javavidsBlog = new Blog();
		javavidsBlog.setName("JavaVids");
		javavidsBlog.setUrl("http://feeds.feedburner.com/javavids?format=xml");
		javavidsBlog.setUser(adminUser);
		blogRepository.save(javavidsBlog);
		
		Item item1 = new Item();
		item1.setBlog(javavidsBlog);
		item1.setTitle("first");
		item1.setLink("http://www.javavids.com/");
		item1.setPublishedDate(new Date());
		itemRepository.save(item1);
		
		Item item2 = new Item();
		item2.setBlog(javavidsBlog);
		item2.setTitle("second");
		item2.setLink("http://www.javavids.com/");
		item2.setPublishedDate(new Date());
		itemRepository.save(item2);
	}
	
}
