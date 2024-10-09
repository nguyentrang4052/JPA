package vn.iotstar.services.impl;

import java.util.List;

import vn.iotstar.dao.ICategoryDao;
import vn.iotstar.dao.impl.CategoryDao;
import vn.iotstar.entity.Category;
import vn.iotstar.services.ICategoryService;

public class CategoryService implements ICategoryService{

	ICategoryDao cateDao = new CategoryDao();
	@Override
	public int count() {
		return cateDao.count();
	}

	@Override
	public List<Category> findAll(int page, int pagesize) {
		return cateDao.findAll(page, pagesize);
	}

	@Override
	public Category findByCategoryname(String catname) throws Exception {
		return cateDao.findByCategoryname(catname);
	}
	

	@Override
	public List<Category> findAll() {
		return cateDao.findAll();
	}

	@Override
	public Category findById(int cateid) {
		return cateDao.findById(cateid);
	}

	@Override
	public void delete(int cateid) throws Exception {
		cateDao.delete(cateid);		
	}

	@Override
	public void update(Category category) {
		cateDao.update(category);		
	}

	@Override
	public void insert(Category category) {
		/*Category cate;
		try {
			cate = this.findByCategoryname(category.getCategoryname());
			if (cate == null)
				cateDao.insert(category);	
		}catch (Exception e){
			e.printStackTrace();
		}*/
		cateDao.insert(category);
	}
}
