/*
* Copyright (c) 2017 by Aisino Wincor Retail & Banking Systems (Shanghai) Co., Ltd,
* Block 70, No.36, Yiwei Rd.,Waigaoqiao Free Trade Zone, Shanghai, China
*
* This software is the confidential and proprietary information
* of Aisino Wincor.
*
* You shall not disclose such confidential information and shall
* use it only in accordance with the terms of the license agreement
* you entered into with Aisino Wincor.
*
*/
package com.hm.attempt.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 
 * @author min_huang
 *
 */
@Entity
@Table(name = "user")
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8281265096968248101L;

	@Column
	@Id()
	private String id;

	@Column
	private String name;

	public User(String id, String name, int age, String gender) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.gender = gender;
	}

	public User(String name, int age, String gender) {
		super();
		this.name = name;
		this.age = age;
		this.gender = gender;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Column
	private int age;

	@Column
	private String gender;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

}
