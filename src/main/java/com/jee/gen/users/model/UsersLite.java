package com.jee.gen.users.model;

import java.io.Serializable;
import java.util.Date;

public class UsersLite implements Serializable {

	private long id;
	private String name;
	private String email;
	private Date lldate;

	public UsersLite() {

	}

	public UsersLite(Users user) {
		this.id = user.getId();
		this.name = user.getName();
		this.email = user.getEmail();
		this.lldate = user.getLldate();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getLldate() {
		return lldate;
	}

	public void setLldate(Date lldate) {
		this.lldate = lldate;
	}

}
