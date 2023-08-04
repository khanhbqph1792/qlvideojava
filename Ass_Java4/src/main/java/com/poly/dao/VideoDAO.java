package com.poly.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import poly.edu.entity.Video;
import poly.edu.utils.JpaUtils;

public class VideoDAO {
	private EntityManager em = JpaUtils.getEntityManager();
	private EntityTransaction tran = em.getTransaction();
	@SuppressWarnings("deprecation")
	protected void finalize() throws Throwable{
		em.close();
		super.finalize();
	}
	public Video insert(Video entity) {
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

	public Video update(Video entity) {
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

	public Video findById(String videoId) {
		Video entity = em.find(Video.class, videoId);
		return entity;
	}

	public List<Video> findAll() {
		TypedQuery<Video> query = em.createNamedQuery("Video.findAll", Video.class);
		List<Video> list = query.getResultList();
		return list;
	}

	public int count() {
		return findAll().size();
	}

//	public boolean checkLogin(String Videoname, String password) {
//		Video Video = findById(Videoname);
//		if (Video != null) {
//			if (Video.getPassword().equals(password)) {
//				return true;
//			}
//		}
//		return false;
//	}
//
//	public boolean checkLogin1(String Videoname, String password) {
//		String jpql = "Select u from Video u where u.id = :Videoname and u.password =:pass";
//		TypedQuery<Video> query = em.createQuery(jpql, Video.class);
//		query.setParameter("Videoname", Videoname);
//		query.setParameter("pass", password);
//		Video Video = query.getSingleResult();
//		if (Video != null) {
//			return true;
//		}
//		return false;
//	}

//	public List<Video> findVideoByVideoname(String name) {
//		String jpql = "Select u from Video u where u.fullname like :name";
//		TypedQuery<Video> query = em.createQuery(jpql, Video.class);
//		query.setParameter("name", "%" + name + "%");
//		return query.getResultList();
//	}
	
	public void delete(String videoId) throws Exception{
		try {
			tran.begin();
			Video entity = em.find(Video.class, videoId);
			if (entity != null) {
				em.remove(entity);
			} else {
				throw new Exception("Loi khong tim thay Video");
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
}

