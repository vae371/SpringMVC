package com.me.app1.dao;

import java.util.Collection;


import javax.validation.ConstraintViolationException;

import org.hibernate.HibernateException;

import org.hibernate.Query;


import com.me.app1.pojo.User;

public class UserDAO extends DAO {

	public UserDAO() {
	}

	public void create(User user)  {
		try {
			begin();
			getSession().save(user);
			commit();
		} catch (ConstraintViolationException e) {
			rollback();	
			System.out.println("fsdf");
			throw e;
		} finally {
			close();
		}
	}

	public Collection<User> SearchUser(String name) {
		Query query = getSession().createQuery("from User where username =:userName");
		query.setParameter("userName", name);
		Collection<User> result = query.list();
		return result;
	}
	
	public User getUser(String name) {
		Query query = getSession().createQuery("from User where username =:userName");
		query.setParameter("userName", name);
		User user = (User) query.uniqueResult();
		return user;
	}

	public User get(String username, String password) throws HibernateException {
		try {
			
			Query q = getSession().createQuery("from User u where u.username = ? and u.password = ?");
			q.setString(0, username);
			q.setString(1, password);
			User user = (User) q.uniqueResult();
			return user;
		} catch (HibernateException e) {
			rollback();
			throw new HibernateException("Could not get user " + e);
		} finally {
			close();
		}
	}
	
	public void createComment(User user) throws HibernateException {
		try {
			begin();
			getSession().saveOrUpdate(user);	
			commit();
		} catch (HibernateException e) {
			rollback();
			throw new HibernateException("Could not get user " + e);
		} finally {
			close();
		}
	}
	
	
	public void changePassword(User user) throws HibernateException {
		try {
			begin();
			getSession().saveOrUpdate(user);	
			commit();
		} catch (HibernateException e) {
			rollback();
			throw new HibernateException("Could not get user " + e);
		} finally {
			close();
		}
	}
	
}