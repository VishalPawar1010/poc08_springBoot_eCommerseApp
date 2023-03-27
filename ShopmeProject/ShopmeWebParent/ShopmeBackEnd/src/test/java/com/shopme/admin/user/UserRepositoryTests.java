package com.shopme.admin.user;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.shopme.common.entity.Role;
import com.shopme.common.entity.User;

import jakarta.persistence.EntityManager;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
@Disabled
public class UserRepositoryTests {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private EntityManager entityManager;
	
	@Test
	public void createNewUserWithOneRole() {
		Role roleAdmin = entityManager.find(Role.class, 1);
		User userVP = new User("vp@gmail.com", "Vishal@123", "Vishal", "Pawar");
		userVP.addRole(roleAdmin);
		
		User savedUser =  userRepo.save(userVP);
		assertThat(savedUser.equals(userVP));
		
	}
	
	@Test
	public void createNewUserWithTwoRole() {
		User userShah = new User("shah@gmail.com", "shah@123", "Dharmesh", "Shah");
		Role roleEditor = new Role(3);
		Role roleAssistant = new Role(5);
		
		userShah.addRole(roleEditor);
		userShah.addRole(roleAssistant);
		
		User savedUser =  userRepo.save(userShah);
		assertThat(savedUser.equals(userShah));
	}
	
	@Test
	public void listAllUsers() {
		Iterable<User> listUsers = userRepo.findAll();
		listUsers.forEach(user -> System.out.println(user));
	}
	
	@Test
	public void testGetUserById() {
		User userVP = userRepo.findById(1).get();
		System.out.println("======>> "+ userVP);
		assertThat(userVP).isNotNull();
	}
	@Test
	public void testUpdateUserDetails() {
		User userVP = userRepo.findById(1).get();
		userVP.setEmail("vishalpawar@gmail.com");
		userVP.setEnabled(true);
		
		
		System.out.println("=================================>> ");
		System.out.println("======>> "+ userVP);
		System.out.println("=================================>> ");

		assertThat(userVP).isNotNull();
	}
	@Test
	public void testUpdateRoleDetails() {
		User userDS = userRepo.findById(2).get();
		Role roleEditor = new Role(3);
//		Role roleSales = new Role(2);

		// bug - remove not working
		userDS.getRoles().remove(roleEditor);
//		userDS.addRole(roleSales);
		userRepo.save(userDS);

	}
	@Test
	public void testDeleteUser() {
		Integer id = 2;
		userRepo.deleteById(id);
	}
	
	

}
