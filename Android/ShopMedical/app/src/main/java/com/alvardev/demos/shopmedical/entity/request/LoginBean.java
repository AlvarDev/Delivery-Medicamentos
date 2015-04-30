package com.alvardev.demos.shopmedical.entity.request;

import java.io.Serializable;

public class LoginBean implements Serializable{

	private String user;
	private String pass;

    public LoginBean(String user, String pass) {
        this.user = user;
        this.pass = pass;
    }

    public LoginBean() {
    }

    /**
	 * @return the user
	 */
	public String getUser() {
		return user;
	}
	/**
	 * @param user the user to set
	 */
	public void setUser(String user) {
		this.user = user;
	}
	/**
	 * @return the pass
	 */
	public String getPass() {
		return pass;
	}
	/**
	 * @param pass the pass to set
	 */
	public void setPass(String pass) {
		this.pass = pass;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "LoginBean [" + (user != null ? "user=" + user + ", " : "")
				+ (pass != null ? "pass=" + pass : "") + "]";
	}
	
	
	
}
