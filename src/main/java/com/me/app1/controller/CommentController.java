package com.me.app1.controller;

import java.text.DateFormat;
import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.me.app1.dao.MessageDAO;
import com.me.app1.pojo.MessageBean;
import com.me.app1.pojo.Movie;



/**
 * Handles requests for the application home page.
 */
@Controller
public class CommentController {
	
	
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value ="/addComment", method = RequestMethod.POST)
	public String home( Model model,HttpServletRequest request) {			
		MessageBean m=new MessageBean();
		HttpSession session=request.getSession();
		Movie movie=(Movie) session.getAttribute("movie");
		m.setContent(request.getParameter("comment"));
		m.setFrom1((String) session.getAttribute("username"));
		m.setTitle(movie.getTitle());		
		MessageDAO messageDAO=new MessageDAO();
		messageDAO.create(m);
		ArrayList<MessageBean> list=messageDAO.SearchMovie(movie.getTitle());
		session.setAttribute("list", list);
		return "showMovie";		
	}
	
	@RequestMapping(value ="/reply", method = RequestMethod.GET)
	public String home1( Model model,HttpServletRequest request) {		
		request.getSession().setAttribute("to", request.getParameter("to"));
		return "reply";		
	}
	
	@RequestMapping(value ="/reply", method = RequestMethod.POST)
	public String home12( Model model,HttpServletRequest request) {		
		MessageBean m=new MessageBean();
		HttpSession session=request.getSession();
		Movie movie=(Movie) session.getAttribute("movie");
		m.setContent(request.getParameter("message"));
		m.setFrom1((String) session.getAttribute("username"));
		m.setTitle(movie.getTitle());
		m.setTo1((String) session.getAttribute("to"));
		MessageDAO messageDAO=new MessageDAO();
		messageDAO.create(m);
		ArrayList<MessageBean> list=messageDAO.SearchMovie(movie.getTitle());
		session.setAttribute("list", list);
		return "showMovie";			
	}
	
}
