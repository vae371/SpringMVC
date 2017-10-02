package com.me.app1.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.me.app1.dao.MovieDAO;
import com.me.app1.dao.UserDAO;
import com.me.app1.pojo.Movie;
import com.me.app1.pojo.User;

@Controller
public class MovieController {

	@RequestMapping(value = "/addMovie", method = RequestMethod.GET)
	public String vieAddMovie(HttpServletRequest request, Map<String, Object> model) {
		HttpSession session = request.getSession();
//		if (!session.getAttribute("userName").equals("admin")) {
//			return "home";
//		}
		return "addMovie";
	}

	@RequestMapping(value = "/movieAdded", method = RequestMethod.POST)
	public String doAddMovie(HttpServletRequest request, Map<String, Object> model,MovieDAO movieDAO ) {
		HttpSession session = request.getSession();
//		if (!session.getAttribute("userName").equals("admin")) {
//			return "home";
//		}

		int number = Integer.parseInt(request.getParameter("num"));		
		
		String[] title = request.getParameterValues("title");
		String[] actor = request.getParameterValues("actor");
		String[] actress = request.getParameterValues("actress");
		String[] year = request.getParameterValues("year");
		for (int i = 0; i < title.length; i++) {
			Movie movie = new Movie();
			movie.setTitle(title[i]);			
			movie.setLeadActor(actor[i]);
			movie.setLeadActress(actress[i]);
			movie.setYear(Integer.valueOf(year[i]));
			try{
				movieDAO.create(movie);	
			}
			catch(ConstraintViolationException e){
				request.setAttribute("error", title[i]);
				return "addMovie";
			}					
		}

		return "redirect:manageMovie";
	}
	
	@RequestMapping(value = "/manageMovie", method = RequestMethod.GET)
	public String manageMovie(HttpServletRequest request, Map<String, Object> model,MovieDAO movieDAO) {
//		HttpSession session = request.getSession();
//		if (session.getAttribute("userName")==null||!session.getAttribute("userName").equals("admin")) {
//			return "home";
//		}
		request.setAttribute("movieList", (ArrayList)movieDAO.SearchMovie());
		return "manageMovie";
	}
	
	
	@RequestMapping(value = "/deleteMovie", method = RequestMethod.GET)
	public String deleteMovie(HttpServletRequest request, Map<String, Object> model,MovieDAO movieDAO) {

		String title=request.getParameter("title");
		movieDAO.delete(title);
		request.setAttribute("movieList", (ArrayList)movieDAO.SearchMovie());
		return "manageMovie";
	}
	
	
}
