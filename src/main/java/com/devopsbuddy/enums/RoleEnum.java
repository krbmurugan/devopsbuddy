package com.devopsbuddy.enums;

public enum RoleEnum {
	BASIC(1, "Role_Basic"),
	PRO(2, "Role_PRO"),
	ADMIN(3, "Role_Admin");
	
	private int roleId;
	
	private String roleName;
	
	private RoleEnum(int id, String roleName) {
		this.roleId=id;
		this.roleName=roleName;
	}

	public int getRoleId() {
		return roleId;
	}

	 

	public String getRoleName() {
		return roleName;
	}
 
	
	

}
