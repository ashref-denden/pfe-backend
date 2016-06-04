package com.ashref.denden.pfe.backend.domain.business;

public class PlatformUserBusiness {
	
	private Long id;
	
	private String username;
	
	private String role;
	
	private String companyName;
	
	private Boolean erpLinked;
	
	public PlatformUserBusiness() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	public String getCompanyName() {
		return companyName;
	}
	
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	public Boolean getErpLinked() {
		return erpLinked;
	}
	
	public void setErpLinked(Boolean erpLinked) {
		this.erpLinked = erpLinked;
	}
}
