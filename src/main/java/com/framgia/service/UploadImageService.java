package com.framgia.service;

import java.io.IOException;

import com.framgia.bean.FileFormInfo;
import com.framgia.bean.ImageInfo;

/**
 * FileUploadService.java
 * 
 * @version 18/04/2017
 * @author phan.van.hieu@framgia.com
 * 
 */
public interface UploadImageService extends BaseService {

	Integer uploadImage(FileFormInfo dataImportBean, String username);

	ImageInfo findById(Integer idImage);

}
