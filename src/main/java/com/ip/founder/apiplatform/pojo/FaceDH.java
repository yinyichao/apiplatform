package com.ip.founder.apiplatform.pojo;

public class FaceDH {
	private String birthday;
	private String external_id;
	private Long face_image_id;
	private String face_image_uri;
	private Integer gender;
	private String name;
	private String person_id;
	private String picture_uri;
	private String picture_url;
	private Long repository_id;
	private Double similarity;

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getExternal_id() {
		return external_id;
	}

	public void setExternal_id(String external_id) {
		this.external_id = external_id;
	}

	public Long getFace_image_id() {
		return face_image_id;
	}

	public void setFace_image_id(Long face_image_id) {
		this.face_image_id = face_image_id;
	}

	public String getFace_image_uri() {
		return face_image_uri;
	}

	public void setFace_image_uri(String face_image_uri) {
		this.face_image_uri = face_image_uri;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPerson_id() {
		return person_id;
	}

	public void setPerson_id(String person_id) {
		this.person_id = person_id;
	}

	public String getPicture_uri() {
		return picture_uri;
	}

	public void setPicture_uri(String picture_uri) {
		this.picture_uri = picture_uri;
	}

	public String getPicture_url() {
		return picture_url;
	}

	public void setPicture_url(String picture_url) {
		this.picture_url = picture_url;
	}

	public Long getRepository_id() {
		return repository_id;
	}

	public void setRepository_id(Long repository_id) {
		this.repository_id = repository_id;
	}

	public Double getSimilarity() {
		return similarity;
	}

	public void setSimilarity(Double similarity) {
		this.similarity = similarity;
	}
}
