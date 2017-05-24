package com.framgia.service;

import java.io.Serializable;

import com.framgia.dao.UserDAO;

/**
 * 
 * @version 22/05/2017
 * @author vu.thi.tran.van@framgia.com
 * 
 */
public interface BaseService extends Serializable {

	UserDAO getUserDAO();
}
