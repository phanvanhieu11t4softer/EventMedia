package com.framgia.bean;

import java.util.List;

/**
 * Created by PhanVanHieu on 23/05/2017.
 */
public class StatisticalInfo {

	private String nameTypeGroup;
	private List<Integer> numberUser;
	private List<String> nameGroup;

	public StatisticalInfo(String nameTypeGroup, List<Integer> numberUser, List<String> nameGroup) {
		super();
		this.nameTypeGroup = nameTypeGroup;
		this.numberUser = numberUser;
		this.nameGroup = nameGroup;
	}

	public String getNameTypeGroup() {
		return nameTypeGroup;
	}

	public void setNameTypeGroup(String nameTypeGroup) {
		this.nameTypeGroup = nameTypeGroup;
	}

	public List<Integer> getNumberUser() {
		return numberUser;
	}

	public void setNumberUser(List<Integer> numberUser) {
		this.numberUser = numberUser;
	}

	public List<String> getNameGroup() {
		return nameGroup;
	}

	public void setNameGroup(List<String> nameGroup) {
		this.nameGroup = nameGroup;
	}

}
