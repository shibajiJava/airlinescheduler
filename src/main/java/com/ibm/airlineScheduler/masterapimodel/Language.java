package com.ibm.airlineScheduler.masterapimodel;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Language {
	@JsonProperty("id")
	private String id;
	@JsonProperty("level")
	private String level;
	@JsonProperty("name")
	private String name;
	@JsonProperty("locale")
	private String locale;
	@JsonProperty("levelDescription")
	private String levelDescription;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocale() {
		return locale;
	}
	public void setLocale(String locale) {
		this.locale = locale;
	}
	public String getLevelDescription() {
		return levelDescription;
	}
	public void setLevelDescription(String levelDescription) {
		this.levelDescription = levelDescription;
	}
	
	
}
