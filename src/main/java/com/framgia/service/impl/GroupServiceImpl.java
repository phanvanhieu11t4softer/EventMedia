package com.framgia.service.impl;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;

import com.framgia.bean.GroupInfo;
import com.framgia.model.Group;
import com.framgia.model.Permission;
import com.framgia.model.User;
import com.framgia.security.CustomUserDetail;
import com.framgia.service.GroupService;
import com.framgia.util.Constants;
import com.framgia.util.ConvetBeanAndModel;
import com.framgia.util.DateUtil;
import com.framgia.util.Helpers;

@SuppressWarnings("serial")
public class GroupServiceImpl extends BaseServiceImpl implements GroupService {

	private static final Logger logger = Logger.getLogger(GroupServiceImpl.class);

	@Override
	public GroupInfo findById(Integer id, boolean flgUpdate) {
		try {
			Group group = getGroupDAO().findById(id, flgUpdate);
			return ConvetBeanAndModel.convertGroupModelToBean(group);
		} catch (Exception e) {
			logger.error("Group service _ findById", e);
			return null;
		}
	}

	@Override
	public boolean createGroup(GroupInfo groupInfo) {

		try {
			CustomUserDetail userDetail = Helpers.getUserDetail();

			User user = hasOwnGroup(Integer.parseInt(userDetail.getUserId()));
			if (user != null) {

				groupInfo.setUserCreate(ConvetBeanAndModel.convertUserModelToBean(user));
				groupInfo.setUserUpdate(Helpers.getUsername());
				groupInfo.setDateCreate(DateUtil.getDateNow());
				groupInfo.setDateUpdate(DateUtil.getDateNow());
				groupInfo.setStatus(Constants.GROUP_STATUS_CODE_ACTIVE);
				groupInfo.setDeleteFlag(Constants.DEL_FLG);

				Group group = getGroupDAO().create(ConvetBeanAndModel.convertGroupBeanToModel(groupInfo));

				if (group == null)
					return false;

				// update user
				user.setStatusJoin(Constants.STATUSJOIN_CODE_APPOVE);
				user.setIdGroup(group.getId());
				user.setPermission(new Permission(Constants.PERMISSION_CODE_MANAGER));

				getUserDAO().update(user);
				return true;
			}
		} catch (Exception e) {
			logger.error("add group", e);
		}

		return false;
	}

	protected User hasOwnGroup(int id) {
		try {
			User user = getUserDAO().findById(id, true);
			if (user != null && user.getIdGroup() == null)
				return user;
		} catch (Exception e) {
			logger.error("isUserExist", e);
		}
		return null;
	}

	@Override
	public boolean updateGroup(GroupInfo groupInfo) {
		try {
			Group group = getGroupDAO().findById(groupInfo.getId(), true);

			if (group == null)
				return false;
			String userUpdate = Helpers.getUsername();
			Date dateUpdate = DateUtil.getDateNow();
			group.setName(groupInfo.getName());
			group.setDescription(groupInfo.getDescription());
			group.setNote(groupInfo.getNote());
			group.setDateStart(DateUtil.convertStringtoDate(groupInfo.getDateStart()));
			group.setDateEnd(DateUtil.convertStringtoDate(groupInfo.getDateEnd()));
			group.setType(Integer.parseInt(groupInfo.getType()));
			group.setStatus(Integer.parseInt(groupInfo.getStatus()));
			group.setDateUpdate(dateUpdate);
			group.setUserUpdate(userUpdate);

			if (Constants.GROUP_STATUS_CODE_INACTIVE.equals(groupInfo.getStatus())) {
				if (!remoteUserInGroup(group, userUpdate, dateUpdate))
					return false;
			}

			getGroupDAO().updateGroup(group);
			return true;
		} catch (Exception e) {
			logger.error("update Group", e);
		}
		return false;
	}

	@Override
	public boolean deleteLogicGroup(Integer id) {
		try {
			Group group = getGroupDAO().findById(id, true);

			if (group == null)
				return false;
			String userUpdate = Helpers.getUsername();
			Date dateUpdate = DateUtil.getDateNow();
			group.setDeleteFlag(Constants.DEL_FLG_DEL);
			group.setDateUpdate(dateUpdate);
			group.setUserUpdate(userUpdate);

			if (!remoteUserInGroup(group, userUpdate, dateUpdate))
				return false;

			getGroupDAO().updateGroup(group);
			return true;
		} catch (Exception e) {
			logger.error("update Group _ Delete logic", e);
		}

		return false;
	}

	protected boolean remoteUserInGroup(Group group, String userUpdate, Date dateUpdate) {
		try {

			// remove all user in group: user.idGroup = null
			for (User item : group.getUser()) {
				if (item == null)
					continue;

				if (item.getPermission().getId().equals(Constants.PERMISSION_CODE_MANAGER)
						&& group.getDeleteFlag().equals(Constants.DEL_FLG))
					continue;

				User user = getUserDAO().findById(item.getId(), true);
				user.setIdGroup(null);
				user.setStatusJoin(Constants.STATUSJOIN_CODE_FREE);
				user.setDateUpdate(dateUpdate);
				user.setUserUpdate(userUpdate);

				if (group.getDeleteFlag() == Constants.DEL_FLG_DEL)
					user.setPermission(new Permission(Constants.PERMISSION_CODE_USER));

				getUserDAO().update(user);
			}

			return true;
		} catch (Exception e) {
			logger.error("remove all user in group", e);
		}
		return false;
	}

	@Override
	public String getNotification(Integer id) {
		try {
			Group group = groupDAO.findByIdUser(id);
			if (group == null) {
				return null;
			}
			Date dateEnd = group.getDateEnd();
			Date systemDate = DateUtil.getDateNow();
			if (dateEnd.before(systemDate)) {
				return "The group expired on the " + DateUtil.convertDatetoString(dateEnd);
			} else {
				long diff = Math.abs(dateEnd.getTime() - systemDate.getTime());
				long diffDays = diff / (24 * 60 * 60 * 1000);
				return "The rest of the time is " + diffDays + ". Expiry date " + DateUtil.convertDatetoString(dateEnd);
			}

		} catch (Exception e) {
			logger.error("remove all user in group", e);
		}

		return null;
	}

}
