package com.poly.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import poly.edu.domain.ShareReport;
import poly.edu.entity.Share;
import poly.edu.utils.JpaUtils;

public class ShareDAO {
	private EntityManager em = JpaUtils.getEntityManager();
	private EntityTransaction tran = em.getTransaction();
	@SuppressWarnings("deprecation")
	protected void finalize() throws Throwable{
		em.close();
		super.finalize();
	}
	public Share insert(Share entity) {
		try {
			tran.begin();
			em.persist(entity);
			tran.commit();
			return entity;
		} catch (Exception e) {
			// TODO: handle exception
			tran.rollback();
			throw e;
		}finally {
			em.close();
		}
	}

	public Share update(Share entity) {
		try {
			tran.begin();
			em.merge(entity);
			tran.commit();
			return entity;
		} catch (Exception e) {
			// TODO: handle exception
			tran.rollback();
			throw e;
		}finally {
			em.close();
		}
	}

	public Share findById(String shareId) {
		Share entity = em.find(Share.class, shareId);
		return entity;
	}

	public List<Share> findAll() {
		TypedQuery<Share> query = em.createNamedQuery("Share.findAll", Share.class);
		List<Share> list = query.getResultList();
		return list;
	}

	public int count() {
		return findAll().size();
	}

//	public boolean checkLogin(String Sharename, String password) {
//		Share Share = findById(Sharename);
//		if (Share != null) {
//			if (Share.getPassword().equals(password)) {
//				return true;
//			}
//		}
//		return false;
//	}
//
//	public boolean checkLogin1(String Sharename, String password) {
//		String jpql = "Select u from Share u where u.id = :Sharename and u.password =:pass";
//		TypedQuery<Share> query = em.createQuery(jpql, Share.class);
//		query.setParameter("Sharename", Sharename);
//		query.setParameter("pass", password);
//		Share Share = query.getSingleResult();
//		if (Share != null) {
//			return true;
//		}
//		return false;
//	}

//	public List<Share> findShareBySharename(String name) {
//		String jpql = "Select u from Share u where u.fullname like :name";
//		TypedQuery<Share> query = em.createQuery(jpql, Share.class);
//		query.setParameter("name", "%" + name + "%");
//		return query.getResultList();
//	}
	
	public void delete(String shareId) throws Exception{
		try {
			tran.begin();
			Share entity = em.find(Share.class, shareId);
			if (entity != null) {
				em.remove(entity);
			} else {
				throw new Exception("Loi khong tim thay Share");
			}
			tran.commit();
		} catch (Exception e) {
			// TODO: handle exception
			tran.rollback();
			throw e;
		}finally {
			em.close();
		}
	}
	
	public List<ShareReport> reportSharesByVideos(String videoId) {
		String jpql = "select new poly.edu.domain.ShareReport(s.user.fullname, s.user.email, s.emails, "
				+ "s.shareDate) from Share s where s.video.videoId = :videoId";
		List<ShareReport> list = null;
		try {
			TypedQuery<ShareReport> query = em.createQuery(jpql, ShareReport.class);
			query.setParameter("videoId", videoId);
			list = query.getResultList();
		} finally {
			em.close();
		}
		return list;
	}
}
