package com.framgia.util;

public interface Constants {

	// Model
	// Constant DEL_FLG {0: Enable}
	public static String DEL_FLG = "0";

	// Constant DEL_FLG_DEL {1: Disable}
	public static String DEL_FLG_DEL = "1";

	// Constant GENDER
	public static String GENDER_CODE_MALE = "0";
	public static String GENDER_CODE_FMALE = "1";
	public static String GENDER_VALUE_MALE = "Male";
	public static String GENDER_VALUE_FMALE = "Fmale";

	// Constant STATUSJOIN
	public static String STATUSJOIN_CODE_FREE = "1";
	public static String STATUSJOIN_CODE_REQUEST = "2";
	public static String STATUSJOIN_CODE_APPOVE = "3";
	public static String STATUSJOIN_VALUE_FREE = "Free";
	public static String STATUSJOIN_VALUE_REQUEST = "Request";
	public static String STATUSJOIN_VALUE_APPOVE = "Approve";

	// Constant Permission
	public static int PERMISSION_CODE_ADMIN = 1;
	public static int PERMISSION_CODE_MANAGER = 2;
	public static int PERMISSION_CODE_USER = 3;
	public static String PERMISSION_VALUE_ADMIN = "Admin";
	public static String PERMISSION_VALUE_MANAGER = "Manage";
	public static String PERMISSION_VALUE_USER = "User";

	// Constant type of Group
	public static String GROUP_TYPE_CODE_PRIVATE = "0";
	public static String GROUP_TYPE_CODE_PUBLIC = "1";
	public static String GROUP_TYPE_VALUE_PRIVATE = "Private";
	public static String GROUP_TYPE_VALUE_PUBLIC = "Public";

	// Constant status of Group
	public static String GROUP_STATUS_CODE_ACTIVE = "0";
	public static String GROUP_STATUS_CODE_INACTIVE = "1";
	public static String GROUP_STATUS_VALUE_ACTIVE = "Active";
	public static String GROUP_STATUS_VALUE_INACTIVE = "Inactive";
}
