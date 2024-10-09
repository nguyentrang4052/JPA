package vn.iotstar.dao.impl;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import vn.iotstar.configs.JpaConfig;
import vn.iotstar.dao.ICategoryDao;
import vn.iotstar.entity.Category;

public class CategoryDao implements ICategoryDao{
	@Override
	public void insert(Category category) {

		//mo ket noi
		EntityManager enma = JpaConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();

		try {

			trans.begin();
			// goi phuong thuc de insert, update, delete
			enma.persist(category);// insert vao bang
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
	public void update(Category category) {

		EntityManager enma = JpaConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();

		try {

			trans.begin();
			// goi phuong thuc de insert, update, delete
			enma.merge(category);
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
	public void delete(int cateid) throws Exception {

		EntityManager enma = JpaConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();

		try {

			trans.begin();
			// goi phuong thuc de insert, update, delete
			Category category = enma.find(Category.class,cateid);
			if (category != null) {
				enma.remove(category);
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
	public Category findById(int cateid){
		EntityManager enma = JpaConfig.getEntityManager();
		//khoi tao
		Category category = enma.find(Category.class, cateid);
		return category;
	}
	
	@Override
	public List<Category>findAll(){
		EntityManager enma = JpaConfig.getEntityManager();
		TypedQuery<Category> query = enma.createNamedQuery("Category.findAll", Category.class);
		return query.getResultList();
	}
	
	@Override
	public List<Category> findByCategoryname(String catname){
		EntityManager enma = JpaConfig.getEntityManager();
		String jpql = "SELECT c FROM Category c WHERE c.catename like :catname"; //entity
		TypedQuery<Category> query = enma.createQuery(jpql, Category.class);
		query.setParameter("catename","%" + catname + "%");
		return query.getResultList();
	}
	
	@Override
	public List<Category>findAll(int page, int pagesize){
		EntityManager enma = JpaConfig.getEntityManager();
		TypedQuery<Category> query = enma.createNamedQuery("Category.findAll", Category.class);
		query.setFirstResult(page * pagesize);
		query.setMaxResults(pagesize);
		return query.getResultList();
	}
	
	@Override
	public int count(){
		EntityManager enma = JpaConfig.getEntityManager();
		String jpql = "SELECT count(c) FROM Category c";
		Query query =  enma.createQuery(jpql);
		return ((Long)query.getSingleResult()).intValue();
	}
	

}
