package com.framgia.dao;

import java.util.List;

import com.framgia.bean.ConditionGroupBean;
import com.framgia.model.Group;

public interface GroupDAO {

	public Group findById(Integer id, boolean isLock);

	public void updateGroup(Group group);

	public Group create(Group group);

	public List<Group> findByGroupType(Integer groupType);

	public List<Group> findByConditon(ConditionGroupBean conditionGroupBean);

	public Long getCountType(Integer type, Integer status, String deleteFlag);

	public Group findByIdUser(Integer idUser);
}
