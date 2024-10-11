package vn.iotstar.services;

import java.util.List;

import vn.iotstar.entity.Video;

public interface IVideoService {
	int count();

	List<Video> findAll(int page, int pagesize);

	Video findByVideoname(String videoname) throws Exception;

	List<Video> findAll();

	Video findById(int videoid);

	void delete(int videoid) throws Exception;

	void update(Video video);

	void insert(Video video);
}
