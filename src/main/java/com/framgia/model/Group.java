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
public class Group implements Serializable {
	private Integer id;
	private String name;
	private String description;
	private String note;
	private Integer type;
	private Integer status;
	private Date dateStart;
	private Date dateEnd;
	private String deleteFlag;
	private String userCreate;
	private Date dateCreate;
	private String userUpdate;
	private Date dateUpdate;
	private List<Image> image = new ArrayList<Image>();
	private List<User> user = new ArrayList<User>();

	public Group() {
		super();
	}

	public Group(Integer id, String name, String description, String note, Integer type, Integer status, Date dateStart,
	        Date dateEnd, String deleteFlag, String userCreate, Date dateCreate, String userUpdate, Date dateUpdate) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.note = note;
		this.type = type;
		this.status = status;
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
		this.deleteFlag = deleteFlag;
		this.userCreate = userCreate;
		this.dateCreate = dateCreate;
		this.userUpdate = userUpdate;
		this.dateUpdate = dateUpdate;
	}

	public void setImage(List<Image> image) {
		this.image = image;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getDateStart() {
		return dateStart;
	}

	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}

	public Date getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}

	public String getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public String getUserCreate() {
		return userCreate;
	}

	public void setUserCreate(String userCreate) {
		this.userCreate = userCreate;
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

	public List<Image> getImage() {
		return image;
	}

	public List<User> getUser() {
		return user;
	}

	public void setUser(List<User> user) {
		this.user = user;
	}

}
