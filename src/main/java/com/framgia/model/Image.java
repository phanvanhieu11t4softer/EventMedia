package com.framgia.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 
 * @version 22/05/2017
 * @author vu.thi.tran.van@framgia.com
 * 
 */
@SuppressWarnings("serial")
public class Image implements Serializable {
	private Integer id;
	private String url;
	private String description;
	private String title;
	private User user;
	private Group group;
	private String deleteFlag;
	private Date dateCreate;
	private String userUpdate;
	private Date dateUpdate;
	List<Vote> vote = new ArrayList<Vote>();

	public Image() {
		super();
	}

	public Image(Integer id, String url, String description, String title, User user, Group group, String deleteFlag,
	        Date dateCreate, String userUpdate, Date dateUpdate) {
		super();
		this.id = id;
		this.url = url;
		this.description = description;
		this.title = title;
		this.user = user;
		this.group = group;
		this.deleteFlag = deleteFlag;
		this.dateCreate = dateCreate;
		this.userUpdate = userUpdate;
		this.dateUpdate = dateUpdate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public String getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public Date getDateCreate() {
		return dateCreate;
	}

	public void setDateCreate(Date dateCreate) {
		this.dateCreate = dateCreate;
	}

	public String getUserUpdate() {
		return userUpdate;
	}

	public void setUserUpdate(String userUpdate) {
		this.userUpdate = userUpdate;
	}

	public Date getDateUpdate() {
		return dateUpdate;
	}

	public void setDateUpdate(Date dateUpdate) {
		this.dateUpdate = dateUpdate;
	}

	public List<Vote> getVote() {
		return vote;
	}

	public void setVote(List<Vote> vote) {
		this.vote = vote;
	}

}
