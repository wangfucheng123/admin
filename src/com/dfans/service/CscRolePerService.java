package com.dfans.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dfans.model.CscRolePermission;

public interface CscRolePerService {
	public String deleRolePermission(Integer rolePermissionId,
			HttpServletResponse response, HttpServletRequest request);
	
	
	public String save_rolePermission(CscRolePermission model,
			HttpServletResponse response, HttpServletRequest request);
	

}
