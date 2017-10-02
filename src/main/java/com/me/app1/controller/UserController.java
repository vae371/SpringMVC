package com.me.app1.controller;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.Cookie;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.me.app1.dao.MessageDAO;
//import com.me.app1.dao.CommentDAO;
import com.me.app1.dao.MovieDAO;
import com.me.app1.dao.UserDAO;
import com.me.app1.pojo.MessageBean;
import com.me.app1.pojo.Movie;
import com.me.app1.pojo.User;

@Controller
public class UserController {

	@RequestMapping(value = "/accountManage", method = RequestMethod.GET)
	public String accountManage(Model model, HttpServletRequest request, HttpServletResponse response,
			UserDAO userDAO) {

		// HttpSession session = request.getSession();
		// String username = (String) session.getAttribute("userName");

		// if (session.getAttribute("userName") == null) {
		// return "home";
		// }

		// session.setAttribute("userName", username);
		// if(username.equals("admin")) return "redirect:adminHome";
		return "accountManage";
	}

	@RequestMapping(value = "/changePassword", method = RequestMethod.POST)
	public String changePassword(Model model, HttpServletRequest request, UserDAO userDAO) {
		String username = (String) request.getSession().getAttribute("username");
		
		String password = request.getParameter("old");
		
		User user = userDAO.get(username, password);
		if (user == null) {
			request.setAttribute("error", "wrong password");
			return "accountManage";
		}
		String newpassword=request.getParameter("new");
		user.setPassword(newpassword);
		userDAO.changePassword(user);		
		return "changePasswordSuccess";
	}

	@RequestMapping(value = "/searchMovie", method = RequestMethod.GET)
	public String viewSearchMovie(Model model, HttpServletRequest request, MovieDAO movieDAO) {		
		return "searchMovie";
	}
	
	@RequestMapping(value = "/searchMovie", method = RequestMethod.POST)
	public String searchMovie(Model model, HttpServletRequest request, MovieDAO movieDAO) {
		String title = request.getParameter("title");
		ArrayList<Movie> movieList=movieDAO.getMovie(title);						
		request.setAttribute("movieList", movieList);
		return "movieResult";
	}
	
	@RequestMapping(value = "/showMovie", method = RequestMethod.GET)
	public String showMovie(Model model, HttpServletRequest request, MovieDAO movieDAO) {
		String title = request.getParameter("title");
		Movie movie=movieDAO.SearchMovie(title);		
		request.getSession().setAttribute("movie", movie);
		MessageDAO messageDAO=new MessageDAO();
		ArrayList<MessageBean> list=messageDAO.SearchMovie(movie.getTitle());
		HttpSession session=request.getSession();
		session.setAttribute("list", list);
//		ArrayList<ha> commentList=commentDAO.SearchComment(title);		
//		request.setAttribute("commentList", commentList);
		return "showMovie";
	}
	
	@RequestMapping(value = "/myMessage", method = RequestMethod.GET)
	public String showMovie1(Model model, HttpServletRequest request, MovieDAO movieDAO) {
		
		HttpSession session=request.getSession();
		MessageDAO messageDAO=new MessageDAO();
		ArrayList<MessageBean> list=messageDAO.SearchMovie1((String) session.getAttribute("username"));
		session.setAttribute("list", list);
//		ArrayList<ha> commentList=commentDAO.SearchComment(title);		
//		request.setAttribute("commentList", commentList);
		return "myMessage";
	}
	
	@RequestMapping(value = "/deleteMessage", method = RequestMethod.GET)
	public String deleteMovie(HttpServletRequest request, Map<String, Object> model,MovieDAO movieDAO) {
		MessageDAO messageDAO=new MessageDAO();
		int id=Integer.valueOf(request.getParameter("id"));
		messageDAO.delete(id);
		HttpSession session=request.getSession();
		ArrayList<MessageBean> list=messageDAO.SearchMovie1((String) session.getAttribute("username"));
		session.setAttribute("list", list);
		
		return "myMessage";
	}
}
