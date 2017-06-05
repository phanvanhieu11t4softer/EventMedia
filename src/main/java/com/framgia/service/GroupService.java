package com.framgia.service;

import java.text.ParseException;

import com.framgia.bean.GroupInfo;

public interface GroupService extends BaseService {
	
	public GroupInfo findById(Integer id, boolean flgUpdate);

	public boolean createGroup(GroupInfo groupInfo) throws Exception;

	public boolean updateGroup(GroupInfo groupInfo) throws ParseException;

	public boolean deleteLogicGroup(Integer id) throws ParseException;

}