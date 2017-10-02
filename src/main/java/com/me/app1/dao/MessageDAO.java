package com.me.app1.dao;

import java.util.ArrayList;
import java.util.Collection;


import javax.validation.ConstraintViolationException;

import org.hibernate.HibernateException;

import org.hibernate.Query;

import com.me.app1.pojo.MessageBean;
import com.me.app1.pojo.Movie;
import com.me.app1.pojo.User;

public class MessageDAO extends DAO {

	public MessageDAO() {
	}

	public void create(MessageBean m)  {
		try {
			begin();
			getSession().save(m);
			commit();
		} catch (ConstraintViolationException e) {
			rollback();				
			throw e;
		} finally {
			close();
		}
	}

	public ArrayList<MessageBean> SearchMovie(String title) {
		Query query = getSession().createQuery("from MessageBean where title= :title");	
		query.setString("title", title);
		ArrayList<MessageBean> result = (ArrayList)query.list();
		return result;
	}
	
	public ArrayList<MessageBean> SearchMovie1(String title) {
		Query query = getSession().createQuery("from MessageBean where from1= :title");	
		query.setString("title", title);
		ArrayList<MessageBean> result = (ArrayList)query.list();
		return result;
	}
	
	public void delete(int id) throws HibernateException {
		try {
			begin();
			Query query = getSession().createQuery("from MessageBean where messageId= :id");
			query.setInteger("id", id);
			MessageBean movie=(MessageBean) query.uniqueResult();
			getSession().delete(movie);
			commit();
		} catch (HibernateException e) {
			rollback();
		}
	}
	
}