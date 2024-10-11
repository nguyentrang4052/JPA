package vn.iotstar.dao.impl;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import vn.iotstar.configs.JpaConfig;
import vn.iotstar.dao.IUserDao;
import vn.iotstar.entity.User;

public class UserDao implements IUserDao {
	@Override
	public void insert(User user) {
		EntityManager enma = JpaConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();

		try {
			trans.begin();
			// goi phuong thuc de insert, update, delete
			enma.persist(user);// insert vao bang
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
	public void update(User user) {
		EntityManager enma = JpaConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();

		try {

			trans.begin();
			// goi phuong thuc de insert, update, delete
			enma.merge(user);
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
	public void delete(int userid) throws Exception {
		EntityManager enma = JpaConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();

		try {

			trans.begin();
			// goi phuong thuc de insert, update, delete
			User user = enma.find(User.class, userid);
			if (user != null) {
				enma.remove(user);
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
	public User findById(int userid){
		EntityManager enma = JpaConfig.getEntityManager();
		//khoi tao
		User user = enma.find(User.class, userid);
		return user;
	}
	
	@Override
	public List<User> findAll(){
		EntityManager enma = JpaConfig.getEntityManager();
		TypedQuery<User> query = enma.createNamedQuery(("User.findAll"), User.class);
		return query.getResultList();
	}
	
	
	 @Override
	public List<User> findByUsername (String username){
			EntityManager enma = JpaConfig.getEntityManager();
			String jpql = "SELECT u FROM User u WHERE u.username LIKE :username"; //entity
			TypedQuery<User> query = enma.createQuery(jpql, User.class);
			query.setParameter("username","%" + username + "%");
			return query.getResultList();
		}
	 
	 @Override
	public List<User>findAll(int page, int pagesize){
			EntityManager enma = JpaConfig.getEntityManager();
			TypedQuery<User> query = enma.createNamedQuery("User.findAll", User.class);
			query.setFirstResult(page * pagesize);
			query.setMaxResults(pagesize);
			return query.getResultList();
		}
}
