package com.example.demo.bean;

import java.math.BigDecimal;

public class Good {

	private String name;
	private int num;
	private BigDecimal unitprice;
	private BigDecimal totalprice;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public BigDecimal getUnitprice() {
		return unitprice;
	}

	public void setUnitprice(BigDecimal unitprice) {
		this.unitprice = unitprice;
	}

	public BigDecimal getTotalprice() {
		return totalprice;
	}

	public void setTotalprice(BigDecimal totalprice) {
		this.totalprice = totalprice;
	}

	@Override
	public String toString() {
		return "名称=" + name + ", 数量=" + num + ", 单价=" + unitprice + ", 总价=" + totalprice + " ";
	}

}
