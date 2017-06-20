package com.framgia.service;

import java.util.List;

import com.framgia.bean.ImageInfo;

/**
 * FileUploadService.java
 * 
 * @version 18/04/2017
 * @author phan.van.hieu@framgia.com
 * 
 */
public interface ManageCrawlerImageService extends BaseService {

	void crawlerData();

	void saveData();

	List<ImageInfo> getListImage(Integer idGroup, Integer numberPageDefault);

	Integer getNoOfRecord(Integer i);

}
