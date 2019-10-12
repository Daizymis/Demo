package com.example.demo.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author min_huang
 *
 */
public enum PayType {

	UNKNOWN(""), COUSUME("消费"), REFUND("退款");
	private final String meaning;
	private int code;

	private static final Map<String, PayType> map = new HashMap<>();

	private PayType(String meaning) {
		this.meaning = meaning;
	}

	public static PayType getEnum(String meaning) {
		return map.get(meaning) == null ? PayType.UNKNOWN : map.get(meaning);
	}

	public String getMeaning() {
		return meaning;
	}

	static {
		for (PayType typeEnum : PayType.values())
			map.put(typeEnum.getMeaning(), typeEnum);
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return meaning;
	}

}
