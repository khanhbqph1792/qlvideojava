package com.poly.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import poly.edu.domain.FavoriteReport;
import poly.edu.domain.FavoriteUserReport;
import poly.edu.entity.Favorite;
import poly.edu.utils.JpaUtils;

public class FavoriteDAO {
	private EntityManager em = JpaUtils.getEntityManager();
	private EntityTransaction tran = em.getTransaction();

	@SuppressWarnings("deprecation")
	protected void finalize() throws Throwable {
		em.close();
		super.finalize();
	}

	public Favorite insert(Favorite entity) {
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

	public Favorite update(Favorite entity) {
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

	public Favorite findById(String favoriteId) {
		Favorite entity = em.find(Favorite.class, favoriteId);
		return entity;
	}

	public List<Favorite> findAll() {
		TypedQuery<Favorite> query = em.createNamedQuery("Favorite.findAll", Favorite.class);
		List<Favorite> list = query.getResultList();
		return list;
	}

	public int count() {
		return findAll().size();
	}

//	public boolean checkLogin(String Favoritename, String password) {
//		Favorite Favorite = findById(Favoritename);
//		if (Favorite != null) {
//			if (Favorite.getPassword().equals(password)) {
//				return true;
//			}
//		}
//		return false;
//	}
//
//	public boolean checkLogin1(String Favoritename, String password) {
//		String jpql = "Select u from Favorite u where u.id = :Favoritename and u.password =:pass";
//		TypedQuery<Favorite> query = em.createQuery(jpql, Favorite.class);
//		query.setParameter("Favoritename", Favoritename);
//		query.setParameter("pass", password);
//		Favorite Favorite = query.getSingleResult();
//		if (Favorite != null) {
//			return true;
//		}
//		return false;
//	}

//	public List<Favorite> findFavoriteByFavoritename(String name) {
//		String jpql = "Select u from Favorite u where u.fullname like :name";
//		TypedQuery<Favorite> query = em.createQuery(jpql, Favorite.class);
//		query.setParameter("name", "%" + name + "%");
//		return query.getResultList();
//	}

	public void delete(String favoriteId) throws Exception {
		try {
			tran.begin();
			Favorite entity = em.find(Favorite.class, favoriteId);
			if (entity != null) {
				em.remove(entity);
			} else {
				throw new Exception("Loi khong tim thay Favorite");
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

	public List<FavoriteUserReport> reportFavoriteUsersByVideos(String videoId) {
		String jpql = "select new poly.edu.domain.FavoriteUserReport(f.user.userId, f.user.fullname, f.user.email, "
				+ "f.likeDate) from Favorite f where f.video.videoId = :videoId";
		List<FavoriteUserReport> list = null;
		try {
			TypedQuery<FavoriteUserReport> query = em.createQuery(jpql, FavoriteUserReport.class);
			query.setParameter("videoId", videoId);
			list = query.getResultList();
		} finally {
			em.close();
		}
		return list;
	}

	public List<FavoriteReport> reportFavoritesByVideos() {
		String jpql = "select new poly.edu.domain.FavoriteReport(f.video.title, count(f), min(f.likeDate), max(f.likeDate))"
				+ " from Favorite f group by f.video.title";
		List<FavoriteReport> list = null;
		try {
			TypedQuery<FavoriteReport> query = em.createQuery(jpql, FavoriteReport.class);
			list = query.getResultList();
		} finally {
			em.close();
		}
		return list;
	}
}
