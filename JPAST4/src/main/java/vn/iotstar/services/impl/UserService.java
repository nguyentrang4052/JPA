package vn.iotstar.services.impl;

import java.util.List;

import vn.iotstar.dao.IUserDao;
import vn.iotstar.dao.impl.UserDao;
import vn.iotstar.entity.User;
import vn.iotstar.services.IUserService;

public class UserService implements IUserService{
	
	IUserDao userdao = new UserDao();

	@Override
	public void insert(User user) {
		userdao.insert(user);		
	}

	@Override
	public void update(User user) {
		userdao.update(user);		
	}

	@Override
	public void delete(int userid) throws Exception {
		userdao.delete(userid);		
	}

	@Override
	public User findById(int userid){
		return userdao.findById(userid);
	}

	@Override
	public List<User> findAll() {
		return userdao.findAll();
	}

	@Override
	public List<User> findByUsername(String username) {
		return userdao.findByUsername(username);
	}

	@Override
	public List<User> findAll(int page, int pagesize) {
		return userdao.findAll(page, pagesize);
	}

}
