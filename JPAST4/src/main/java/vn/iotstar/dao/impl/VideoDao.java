package vn.iotstar.dao.impl;


import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import vn.iotstar.configs.JpaConfig;
import vn.iotstar.dao.IVideoDao;
import vn.iotstar.entity.Video;

public class VideoDao implements IVideoDao{
	@Override
	public void insert(Video video) {
		EntityManager enma = JpaConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();

		try {

			trans.begin();
			// goi phuong thuc de insert, update, delete
			enma.persist(video);// insert vao bang
			trans.commit();

		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;

		} finally {
			enma.close();
		}
	}
	
	@Override
	public void update(Video video) {
		EntityManager enma = JpaConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();

		try {

			trans.begin();
			// goi phuong thuc de insert, update, delete
			enma.merge(video);
			trans.commit();

		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;

		} finally {
			enma.close();
		}
	}
	
	@Override
	public void delete(int videoid) throws Exception {
		EntityManager enma = JpaConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();

		try {

			trans.begin();
			// goi phuong thuc de insert, update, delete
			Video video = enma.find(Video.class, videoid);
			if (video != null) {
				enma.remove(video);
			}else {
				throw new Exception("Khong tim thay");
			}
			
			trans.commit();

		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;

		} finally {
			enma.close();
		}
	}
	
	@Override
	public Video findById(int videoid){
		EntityManager enma = JpaConfig.getEntityManager();
		//khoi tao
		Video video = enma.find(Video.class, videoid);
		return video;
	}
	
	@Override
	public List<Video> findAll(){
		EntityManager enma = JpaConfig.getEntityManager();
		TypedQuery<Video> query = enma.createNamedQuery(("Video.findAll"), Video.class);
		return query.getResultList();
	}
	
	@Override
	public List<Video> findByVideoname (String videoname){
		EntityManager enma = JpaConfig.getEntityManager();
		String jpql = "SELECT v FROM Video v WHERE v.title LIKE :videoname"; //entity
		TypedQuery<Video> query = enma.createQuery(jpql, Video.class);
		query.setParameter("videoname","%" + videoname + "%");
		return query.getResultList();
	}
	
	@Override
	public List<Video>findAll(int page, int pagesize){
		EntityManager enma = JpaConfig.getEntityManager();
		TypedQuery<Video> query = enma.createNamedQuery("Video.findAll", Video.class);
		query.setFirstResult(page * pagesize);
		query.setMaxResults(pagesize);
		return query.getResultList();
	}
	
	@Override
	public int count(){
		EntityManager enma = JpaConfig.getEntityManager();
		String jpql = "SELECT video(v) FROM Video v";
		Query query =  enma.createQuery(jpql);
		return ((Long)query.getSingleResult()).intValue();
	}

}
