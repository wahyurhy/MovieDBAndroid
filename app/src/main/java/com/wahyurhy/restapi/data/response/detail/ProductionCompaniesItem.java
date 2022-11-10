package com.wahyurhy.restapi.data.response.detail;

import com.google.gson.annotations.SerializedName;

public class ProductionCompaniesItem{

	@SerializedName("logo_path")
	private Object logoPath;

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private int id;

	@SerializedName("origin_country")
	private String originCountry;

	public Object getLogoPath(){
		return logoPath;
	}

	public String getName(){
		return name;
	}

	public int getId(){
		return id;
	}

	public String getOriginCountry(){
		return originCountry;
	}
}