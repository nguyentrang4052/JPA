package vn.iotstar.dao;

import java.util.List;

import vn.iotstar.entity.User;

public interface IUserDao {
	void insert(User user);
	
	void update(User user);
	
	void delete(int userid) throws Exception;
	
	User findById(int userid);
	
	List<User> findAll();
	
	List<User> findByUsername (String username);
	
	List<User>findAll(int page, int pagesize);
}
