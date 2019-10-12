package com.example.demo.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 银行交易
 * 
 * @author min_huang
 *
 */
public class BankRecord implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 935397074328196973L;

	private String transactionDate;// 交易时间

	private String orderId;// 订单号

	private String serialId;// 流水号

	private String thirdOrderId;// 第三方订单号

	private String store;// 店面

	private PayType payType;// 交易类型

	private String trading;// 交易方式

	private BigDecimal amount;// 交易金额

	private BigDecimal serviceCharge;// 手续费

	private String invoiceStatus;// 开票状态

	private String remark;// 附加信息

	public String getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getSerialId() {
		return serialId;
	}

	public void setSerialId(String serialId) {
		this.serialId = serialId;
	}

	public String getThirdOrderId() {
		return thirdOrderId;
	}

	public void setThirdOrderId(String thirdOrderId) {
		this.thirdOrderId = thirdOrderId;
	}

	public String getStore() {
		return store;
	}

	public void setStore(String store) {
		this.store = store;
	}

	public PayType getPayType() {
		return payType;
	}

	public void setPayType(PayType payType) {
		this.payType = payType;
	}

	public String getTrading() {
		return trading;
	}

	public void setTrading(String trading) {
		this.trading = trading;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getServiceCharge() {
		return serviceCharge;
	}

	public void setServiceCharge(BigDecimal serviceCharge) {
		this.serviceCharge = serviceCharge;
	}

	public String getInvoiceStatus() {
		return invoiceStatus;
	}

	public void setInvoiceStatus(String invoiceStatus) {
		this.invoiceStatus = invoiceStatus;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "BRecord [transactionDate=" + transactionDate + ", orderId=" + orderId + ", serialId=" + serialId
				+ ", thirdOrderId=" + thirdOrderId + ", store=" + store + ", payType=" + payType + ", trading="
				+ trading + ", amount=" + amount + ", serviceCharge=" + serviceCharge + ", invoiceStatus="
				+ invoiceStatus + ", remark=" + remark + "]";
	}

}
