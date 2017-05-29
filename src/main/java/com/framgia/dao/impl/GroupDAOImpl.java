package com.framgia.dao.impl;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.criterion.Restrictions;

import com.framgia.dao.AbstractDAO;
import com.framgia.dao.GroupDAO;
import com.framgia.model.Group;
import com.framgia.util.Constants;

/**
 * 
 * @version 22/05/2017
 * @author phan.van.hieu@framgia.com
 * 
 */
public class GroupDAOImpl extends AbstractDAO<Integer, Group> implements GroupDAO {

	private static final Logger logger = Logger.getLogger(GroupDAOImpl.class);

	@SuppressWarnings("deprecation")
	@Override
	public Group findById(Integer idUser, boolean isLock) {
		logger.info("Search user to update");
		Criteria crit = getSession().createCriteria(Group.class);
		crit.add(Restrictions.eq("deleteFlag", Constants.DEL_FLG));

		crit.add(Restrictions.eq("id", idUser));

		if (isLock) {
			crit.setLockMode(LockMode.UPGRADE);
		}

		return (Group) crit.uniqueResult();
	}

	@Override
	public void updateGroup(Group group) {
		logger.info("Update Group");
		saveOrUpdate(group);
	}
}
