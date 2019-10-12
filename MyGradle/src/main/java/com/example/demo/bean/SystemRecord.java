package com.example.demo.bean;

import java.math.BigDecimal;
import java.util.List;

/**
 * 
 * @author min_huang
 *
 */
public class SystemRecord {

	private String transactionDate;// 交易时间

	private BigDecimal amount;// 实收金额

	private BigDecimal cashAmount;// 现金收款

	private BigDecimal wechatAmount;// 微信收款

	private BigDecimal aliAmount;// 支付宝收款

	private String goodsName;// 商品名称

	private String shopName;// 店铺名称

	private String transactionId;// 交易号

	private List<Good> goods;// 商品列表

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public List<Good> getGoods() {
		return goods;
	}

	public void setGoods(List<Good> goods) {
		this.goods = goods;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getCashAmount() {
		return cashAmount;
	}

	public void setCashAmount(BigDecimal cashAmount) {
		this.cashAmount = cashAmount;
	}

	public BigDecimal getWechatAmount() {
		return wechatAmount;
	}

	public void setWechatAmount(BigDecimal wechatAmount) {
		this.wechatAmount = wechatAmount;
	}

	public BigDecimal getAliAmount() {
		return aliAmount;
	}

	public void setAliAmount(BigDecimal aliAmount) {
		this.aliAmount = aliAmount;
	}

	@Override
	public String toString() {
		return "ARecord [transactionDate=" + transactionDate + ", amount=" + amount + ", cashAmount=" + cashAmount
				+ ", wechatAmount=" + wechatAmount + ", aliAmount=" + aliAmount + "]";
	}

}
