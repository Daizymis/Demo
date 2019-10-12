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

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hm.attempt.dao.UserDao;
import com.hm.attempt.entity.User;

/**
 * 
 * @author min_huang
 *
 */
@RestController
@RequestMapping("/hm")
@ResponseBody
public class SolveProblemController {

	@Autowired
	private UserDao userDao;

	@RequestMapping(path = "/a", method = RequestMethod.GET)
	public Map<String, String> getHelloWorld() {
		Map<String, String> a = new HashMap<String, String>();
		a.put("a", "b");
		return a;
	}

	@RequestMapping(path = "/b", method = RequestMethod.GET)
	public String getB() {
		return "a";
	}

	@RequestMapping(path = "/c", method = RequestMethod.GET)
	public String setUser() {
		userDao.save(new User("37957397539739", "lili", 18, "女"));
		return userDao.findAll().toString();

	}
}
