package com.shiva.formmodel;

public class RoleForm {

	private Integer authoriteId;
	private String authority;

	public RoleForm(String authority) {
		super();
		this.authority = authority;
	}

	public RoleForm() {

	}

	public Integer getAuthoriteId() {
		return authoriteId;
	}

	public void setAuthoriteId(Integer authoriteId) {
		this.authoriteId = authoriteId;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	@Override
	public String toString() {
		return "RoleForm [authoriteId=" + authoriteId + ", authority=" + authority + "]";
	}

}
