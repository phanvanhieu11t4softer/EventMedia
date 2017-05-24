package com.framgia.security;

public enum PermissionType {

	ADMIN("ADMIN"), MANAGER("MANAGER"), USER("USER");

	String permissionType;

	private PermissionType(String permissionType) {
		this.permissionType = permissionType;
	}

	public String getPermissionType() {
		return permissionType;
	}

}
