package com.shopme.AngJS;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/")
public class MainController {

	
	@RequestMapping(value = "/x", method = RequestMethod.GET)
	public String viewHomePage() {
		return "index";
	}

}
