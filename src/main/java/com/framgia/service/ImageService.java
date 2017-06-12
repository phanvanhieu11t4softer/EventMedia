package com.framgia.service;

import java.text.ParseException;
import java.util.List;

import com.framgia.bean.ImageInfo;

public interface ImageService extends BaseService {

	public ImageInfo findById(Integer id, boolean flgUpdate);

	public boolean removeImageInGroup(Integer id);

	public ImageInfo getImageByUserCreate(String username, Integer idGroup);

	public List<ImageInfo> getListImage(String condition, int page);

	public Integer getNoOfRecord(String condition);

	boolean addVote(Integer idImage, Integer idUser) throws ParseException;

	boolean remoteVote(Integer id, Integer idUser);
}
