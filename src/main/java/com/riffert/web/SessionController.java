package com.riffert.web;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SessionController
{
	
	@RequestMapping(value="/session")
	public String delete(Model model,HttpSession session,
			@RequestParam(defaultValue="0",name="value")String value)
	{
			System.out.println("value = "+value);
			session.setAttribute("value", value);
			
			return "redirect:/";
	}

}
