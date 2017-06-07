package com.framgia.dao;

import java.util.List;

import com.framgia.model.Image;

public interface ImageDAO {

	public Image findById(Integer id, boolean isLock);

	public Image getImageByUserCreate(String username, Integer idGroup);

	public void update(Image image);

	Long findByUserCreate(String username);

	Image findImage(String username);

	public List<Image> getListImage(String condition, int first, int max);

	public Integer getNoOfRecord(String condition);
}
