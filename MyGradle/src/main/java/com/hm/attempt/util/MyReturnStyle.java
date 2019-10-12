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
package com.hm.attempt.util;

import org.apache.commons.lang3.SystemUtils;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 
 * @author min_huang
 *
 */
public class MyReturnStyle extends ToStringStyle {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1860967347875883840L;

	public MyReturnStyle() {
		this.setUseShortClassName(true);
		this.setUseIdentityHashCode(false);
		this.setFieldSeparator(SystemUtils.LINE_SEPARATOR + " ");
		this.setContentStart("{");

		this.setFieldSeparatorAtStart(true);
		this.setContentEnd("}");
	}

	public void appendDetail(StringBuffer buffer, String fieldName, Object value) {
		super.appendDetail(buffer, fieldName, value);
	}

}
