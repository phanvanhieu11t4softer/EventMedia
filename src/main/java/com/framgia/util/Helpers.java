package com.framgia.util;

import org.springframework.security.core.context.SecurityContextHolder;

import com.framgia.security.CustomUserDetail;

public class Helpers {

	public static CustomUserDetail getUserDetail() {
		return (CustomUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}

	public static Integer getIdUser() {
		CustomUserDetail userDetail = null;
		try {
			userDetail = (CustomUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (userDetail == null) {
				return 0;
			}
		} catch (Exception e) {
			return 0;
		}
		return Integer.valueOf(userDetail.getUserId());
	}

	public static String getUsername() {
		CustomUserDetail userDetail = null;
		try {
			userDetail = (CustomUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (userDetail == null) {
				return null;
			}
		} catch (Exception e) {
			return null;
		}
		return userDetail.getUsername();
	}
}
