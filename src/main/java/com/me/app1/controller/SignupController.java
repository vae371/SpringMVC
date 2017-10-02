package com.me.app1.controller;

import java.io.ByteArrayOutputStream;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Base64;
import java.util.Collection;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.validation.Valid;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.me.app1.dao.UserDAO;
import com.me.app1.pojo.User;
import com.me.app1.service.MailService;

import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

@Controller
public class SignupController {
	
	
	
	
    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String viewSignup(Map<String, Object> model) {
        User user=new User();
        model.put("userForm", user);        
        return "signup";
    }
 
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String doSignup(@Valid @ModelAttribute("userForm") User userForm,
            BindingResult result, Map<String, Object> model,UserDAO userDAO,HttpServletRequest hsr) {
 
        if (result.hasErrors()) {
            return "signup";
        }
        
        
        
        try{
        	userDAO.create(userForm);   
		}
		catch(ConstraintViolationException e){
			hsr.setAttribute("error", userForm.getUsername());			
			return "signup";
		}				

        try {
        	
			String from = "dengxu371@gmail.com";
			String username = from.substring(0, from.indexOf("@"));
			String password="13037399976";
			
			MailService mailService = new MailService();
			mailService.sendSimpleMail(username, password, from,userForm.getEmail() ,
					                  "Notification", 
					                  "Welcome"+userForm.getUsername());
		
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        return "signupSuccess";
    }
    
   
    
    @RequestMapping(value = "/checkUser1.htm", method=RequestMethod.GET)
	@ResponseBody
	public String checkUser(HttpServletRequest hsr, HttpServletResponse hsr1,UserDAO userDAO){
		String name = hsr.getParameter("username");
		
		Collection<User> result = userDAO.SearchUser(name);
		if(result.isEmpty()){
			try {
				PrintWriter out = hsr1.getWriter();
				out.println("true");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			Json obj=new Json();
//			obj.put("true",true);
			System.out.println("111111");
			return "true";
		}
		else{
			System.out.println("222222");
			return "";
		}
	}
}
