package com.shopme.admin.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopme.common.entity.User;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepo;
	
	public List<User> listAll(){
		List<User> userList = (List<User>) userRepo.findAll();
		return userList ;
		
	}

}
