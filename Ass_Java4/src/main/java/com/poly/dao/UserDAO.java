package com.poly.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import poly.edu.entity.User;
import poly.edu.utils.JpaUtils;

public class UserDAO {
	private EntityManager em = JpaUtils.getEntityManager();
	private EntityTransaction tran = em.getTransaction();

	@SuppressWarnings("deprecation")
	protected void finalize() throws Throwable {
		em.close();
		super.finalize();
	}

	public User insert(User entity) {
		try {
			tran.begin();
			em.persist(entity);
			tran.commit();
			return entity;
		} catch (Exception e) {
			// TODO: handle exception
			tran.rollback();
			throw e;
		} finally {
			em.close();
		}
	}

	public User update(User entity) {
		try {
			tran.begin();
			em.merge(entity);
			tran.commit();
			return entity;
		} catch (Exception e) {
			// TODO: handle exception
			tran.rollback();
			throw e;
		} finally {
			em.close();
		}
	}

	public User findById(String userId) {
		User entity = em.find(User.class, userId);
		return entity;
	}

	public List<User> findAll() {
		TypedQuery<User> query = em.createNamedQuery("User.findAll", User.class);
		List<User> list = query.getResultList();
		return list;
	}

	public int count() {
		return findAll().size();
	}

	public void delete(String userId) throws Exception {
		try {
			tran.begin();
			User entity = em.find(User.class, userId);
			if (entity != null) {
				em.remove(entity);
			} else {
				throw new Exception("Loi khong tim thay user");
			}
			tran.commit();
		} catch (Exception e) {
			// TODO: handle exception
			tran.rollback();
			throw e;
		} finally {
			em.close();
		}
	}

	public void changePassword(String userId, String oldPassword, String newPassword) throws Exception {
		String jpql = "Select u From User u where u.userId = :userId and u.password = :password";
		try {
			tran.begin();
			TypedQuery<User> query = em.createQuery(jpql, User.class);
			query.setParameter("userId", userId);
			query.setParameter("password", oldPassword);
			User entity = query.getSingleResult();
			if (entity == null) {
				throw new Exception("Current password or UsernameId are incorrect");
			}
			entity.setPassword(newPassword);
			em.merge(entity);
			tran.commit();
		} catch (Exception e) {
			// TODO: handle exception
			tran.rollback();
			throw e;
		} finally {
			em.close();
		}
	}

	public User findByUserIdAndEmail(String userId, String email) {
		String jpql = "Select u From User u where u.userId = :userId and u.email = :email";
		try {
			TypedQuery<User> query = em.createQuery(jpql, User.class);
			query.setParameter("userId", userId);
			query.setParameter("email", email);
			return query.getSingleResult();
		} finally {
			em.close();
		}
	}
}
