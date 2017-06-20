package com.framgia.dao;

import java.util.List;

import com.framgia.model.Image;

public interface ImageDAO extends IGenericDAO<Integer, Image> {

	Image findById(Integer id, boolean isLock);

	Image getImageByUserCreate(String username, Integer idGroup);

	Long findByUserCreate(String username);

	Image findImage(String username);

	List<Image> getListImage(String condition, int first, int max);

	Integer getNoOfRecord(String condition);

	Long count(String string, String string2);

	List<Image> findListImage(int idGroup, int i, int numberPageLimit);

	Integer getNumberRecord(Integer i);
}
