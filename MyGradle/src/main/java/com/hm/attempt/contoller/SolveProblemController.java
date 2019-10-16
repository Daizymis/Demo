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
package com.hm.attempt.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hm.attempt.dao.UserMapper;
import com.hm.attempt.entity.User;

/**
 * 
 * @author min_huang
 *
 */
@Controller
public class SolveProblemController {

	@Autowired
	UserMapper userMapper;

	@RequestMapping(path = "/index")
	public String getHelloWorld() {
		return "index";
	}

	@RequestMapping(path = "/b")
	public String getB() {
		return "a";
	}

	@RequestMapping(path = "/c", method = RequestMethod.GET)
	@ResponseBody
	public String setUser() {

		userMapper.insertUser(new User("lili", 18, "女"));
		return userMapper.findByName("aa").toString();

	}
}
