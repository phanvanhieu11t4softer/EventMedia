package com.framgia.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.framgia.bean.ConditionUserBean;
import com.framgia.dao.GenericDAO;
import com.framgia.dao.UserDAO;
import com.framgia.model.User;
import com.framgia.util.Constants;
import com.framgia.util.Helpers;

/**
 * 
 * @version 22/05/2017
 * @author vu.thi.tran.van@framgia.com
 * 
 */
public class UserDAOImpl extends GenericDAO<Integer, User> implements UserDAO {

	private static final Logger logger = Logger.getLogger(UserDAOImpl.class);

	public UserDAOImpl() {
		super(User.class);
	}

	@Override
	public User findByUserName(String username) {
		logger.info("UserDAO _ findByUsername");
		User user = new User();
		Criteria crit = getSession().createCriteria(User.class);
		crit.add(Restrictions.eq("deleteFlag", Constants.DEL_FLG));
		crit.add(Restrictions.eq("username", username));
		user = (User) crit.uniqueResult();
		return user;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findByConditon(ConditionUserBean conditionUserBean) {
		logger.info("Search list user.");
		Criteria criterion = getSession().createCriteria(User.class);
		criterion.add(Restrictions.eq("deleteFlag", Constants.DEL_FLG));
		if (conditionUserBean.getUserName() != null) {
			if (null != conditionUserBean.getUserName() && !Helpers.isEmpty(conditionUserBean.getUserName())) {
				criterion.add(Restrictions.like("username", "%" + conditionUserBean.getUserName() + "%"));
			}
			if (conditionUserBean.getPermissioName() > 0) {
				criterion.add(Restrictions.eq("permission.id", conditionUserBean.getPermissioName()));
			}
		}

		return criterion.list();
	}

	@SuppressWarnings("deprecation")
	@Override
	public User findById(Integer id, boolean isLock) {
		logger.info("UserDAO _ findByUsername");
		Criteria crit = getSession().createCriteria(User.class);
		crit.add(Restrictions.eq("deleteFlag", Constants.DEL_FLG));
		crit.add(Restrictions.eq("id", id));
		if (isLock) {
			crit.setLockMode(LockMode.UPGRADE);
		}
		return (User) crit.uniqueResult();
	}

	@Override
	public Long getCountUser(Integer id) {

		Criteria crit = getSession().createCriteria(User.class);
		crit.add(Restrictions.eq("deleteFlag", Constants.DEL_FLG));
		crit.add(Restrictions.eq("idGroup", id));
		crit.setProjection(Projections.rowCount());
		return (Long) crit.uniqueResult();

	}

	@Override
	public List<User> getListUser() {
		
		logger.info("Search list user.");
		Criteria criterion = getSession().createCriteria(User.class);
		return criterion.list();
	}

}
