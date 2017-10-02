package com.me.app1.dao;

import java.util.ArrayList;

import java.util.Collection;

import javax.validation.ConstraintViolationException;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;

import org.hibernate.Query;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import com.me.app1.pojo.Movie;
import com.me.app1.pojo.Movie;

public class MovieDAO extends DAO {

	public MovieDAO() {
	}

	public void create(Movie movie)  {
		try {
			begin();
			getSession().save(movie);
			commit();
		} catch (ConstraintViolationException e) {
			rollback();
			throw e;
		} finally {
			close();
		}
	}

	public ArrayList<Movie> SearchMovie() {
		Query query = getSession().createQuery("from Movie ");		
		ArrayList<Movie> result = (ArrayList)query.list();
		return result;
	}

	public Movie SearchMovie(String name) {
		Query query = getSession().createQuery("from Movie where title =:title");
		query.setParameter("title", name);
		Movie result = (Movie) query.uniqueResult();
		return result;
	}
	
	public  ArrayList<Movie> getMovie(String name) {
		Criteria crit=getSession().createCriteria(Movie.class);
		crit.add(Restrictions.like("title",name,MatchMode.ANYWHERE));
		ArrayList<Movie> result = (ArrayList)crit.list();		
		return result;
	}

	public void delete(String title) throws HibernateException {
		try {
			begin();
			Movie movie=SearchMovie(title);
			getSession().delete(movie);
			commit();
		} catch (HibernateException e) {
			rollback();
		}
	}
}