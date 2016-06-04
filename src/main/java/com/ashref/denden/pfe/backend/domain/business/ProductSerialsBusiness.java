package com.ashref.denden.pfe.backend.domain.business;

import java.util.ArrayList;
import java.util.List;

public class ProductSerialsBusiness {
	
	private String name;
	
	private String description;
	
	private List<SerialBusiness> serials = new ArrayList<SerialBusiness>();
	
	public ProductSerialsBusiness() {
		// TODO Auto-generated constructor stub
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

	public List<SerialBusiness> getSerials() {
		return serials;
	}

	public void setSerials(List<SerialBusiness> serials) {
		this.serials = serials;
	}
	
}
