package com.me.app1.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.me.app1.dao.UserDAO;
import com.me.app1.pojo.User;

@Controller
public class LoginController {

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String doLogin(Model model, HttpServletRequest request, HttpServletResponse response, UserDAO userDAO) {

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		Cookie userCookie = new Cookie("username", request.getParameter("username"));
		Cookie passwordCookie = new Cookie("password", request.getParameter("password"));
		response.addCookie(userCookie);
		response.addCookie(passwordCookie);

		User user = userDAO.get(username, password);

		if (user == null) {
			request.setAttribute("error", "error");
			return "home";
		}

		HttpSession session = request.getSession();
		session.setAttribute("username", username);
		
		
		if (username.equals("admin"))
			return "redirect:adminHome";

		return "userHome";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String doLogin1(Model model, HttpServletRequest request, HttpServletResponse response, UserDAO userDAO) {
//
//		HttpSession session = request.getSession();		
//		if (session.getAttribute("userName") == null) {
//			return "home";
//		}	
//
//		if (session.getAttribute("userName").equals("admin"))
//			return "redirect:adminHome";
		
		return "userHome";
	}

	@RequestMapping(value = "/adminHome")
	public String adminLogin(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session.getAttribute("username") == null) {
			return "redirect:home";
		}	

		if (!session.getAttribute("username").equals("admin"))
			return "redirect:login";
		
		
		return "adminHome";
	}

	@RequestMapping(value = "/logout")
	public String doLogout(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		return "redirect:home";
	}
}
