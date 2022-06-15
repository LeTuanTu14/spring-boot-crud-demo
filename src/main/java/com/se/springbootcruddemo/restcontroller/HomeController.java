package com.se.springbootcruddemo.restcontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping("/")
    public String showHomePage() {
		return "redirect:/api/employees";
    }


}
