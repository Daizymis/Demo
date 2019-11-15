package com.example.demo.service;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.swing.JTextArea;

import com.example.demo.bean.BankRecord;
import com.example.demo.bean.Good;
import com.example.demo.bean.PayType;
import com.example.demo.bean.SystemRecord;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class ReadExcel {

	public List<SystemRecord> aList = new ArrayList<>();
	public List<BankRecord> bList = new ArrayList<>();

	private Date currentDate;

	private JTextArea area = new JTextArea();

	public JTextArea getArea() {
		return area;
	}

	public void setArea(JTextArea area) {
		this.area = area;
	}

	public ReadExcel(JTextArea area) {
		super();
		this.area = area;
	}

	public ReadExcel() {
		super();
	}

	public static void main(String[] args) {
		ReadExcel r = new ReadExcel();
		// 7月：20190912105435037 8月：20190912164112526
		File bankfile = new File("D:/A对账/bank/20190912105435037.xls");
		File detailfile = new File("D:/A对账/bank/自营店铺明细报表10-30.XLS");
		r.compare(detailfile, bankfile);

		if (!r.aList.isEmpty() || !r.bList.isEmpty()) {
			r.writeExcel(r.aList, r.bList);
		}

	}

	public void writeExcel(List<SystemRecord> l1, List<BankRecord> l2) {
		area.append("准备写入excel\n");
		File file = new File("D:/A对账/result/对账结果.xls");
		WritableWorkbook wwork = null;
		try {
			if (file.exists()) {
				copyFile();
				if (!file.delete()) {
					throw new Exception("删除文件出现异常");
				}
			}
			wwork = Workbook.createWorkbook(file);
			wwork.createSheet("sheet1", 0);

			WritableSheet sheet = wwork.getSheet(0);
			int rownumber = 0;
			if (!l1.isEmpty()) {
				Label label = new Label(1, 0, "自营店铺明细表");
				Label title0 = new Label(0, 1, "交易号");
				Label title1 = new Label(1, 1, "交易日期");
				Label title2 = new Label(2, 1, "店铺名称");
				Label title3 = new Label(3, 1, "支付宝");
				Label title7 = new Label(4, 1, "商品名称");
				Label title4 = new Label(5, 1, "微信");

				Label title6 = new Label(7, 1, "支付金额");

				sheet.addCell(label);
				sheet.addCell(title0);
				sheet.addCell(title1);
				sheet.addCell(title2);
				sheet.addCell(title3);
				sheet.addCell(title4);

				sheet.addCell(title6);
				sheet.addCell(title7);
				for (int i = 0; i < l1.size(); i++) {
					Label label0 = new Label(0, rownumber + 2 + i, l1.get(i).getTransactionId());
					Label label1 = new Label(1, rownumber + 2 + i, l1.get(i).getTransactionDate());
					Label label2 = new Label(7, rownumber + 2 + i, l1.get(i).getAmount().toString());
					Label label7 = new Label(4, rownumber + 2 + i, l1.get(i).getGoods().toString());
					Label label3 = new Label(2, rownumber + 2 + i, l1.get(i).getShopName());
					Label label4 = new Label(5, rownumber + 2 + i, l1.get(i).getWechatAmount().toString());
					Label label5 = new Label(3, rownumber + 2 + i, l1.get(i).getAliAmount().toString());
					sheet.addCell(label7);
					sheet.addCell(label1);
					sheet.addCell(label0);
					sheet.addCell(label2);
					sheet.addCell(label3);
					sheet.addCell(label4);
					sheet.addCell(label5);
				}

			}
			if (!l2.isEmpty()) {
				rownumber = sheet.getRows();
				Label label = new Label(0, rownumber, "爱信宝");
				Label title1 = new Label(0, rownumber + 1, "交易日期");

				Label title2 = new Label(1, rownumber + 1, "金额");
				Label title3 = new Label(2, rownumber + 1, "流水号");
				Label title4 = new Label(3, rownumber + 1, "交易方式");

				sheet.addCell(label);
				sheet.addCell(title1);
				sheet.addCell(title2);
				sheet.addCell(title3);
				sheet.addCell(title4);

				for (int i = 0; i < l2.size(); i++) {

					Label label1 = new Label(0, rownumber + 2 + i, l2.get(i).getTransactionDate());
					Label label2 = new Label(1, rownumber + 2 + i, l2.get(i).getAmount().toString());
					Label label3 = new Label(2, rownumber + 2 + i, l2.get(i).getSerialId());
					Label label4 = new Label(3, rownumber + 2 + i, l2.get(i).getPayType().getMeaning());

					sheet.addCell(label1);
					sheet.addCell(label2);
					sheet.addCell(label3);
					sheet.addCell(label4);
				}

			}
			wwork.write();
		}

		catch (

		FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (RowsExceededException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (wwork != null) {
				try {
					wwork.close();
				} catch (WriteException | IOException e) {
					e.printStackTrace();
				}
			}
		}
		area.append("写入完成\n");
	}

	public void copyFile() {
		Workbook wk = null;
		boolean flag = true;

		try {
			wk = Workbook.getWorkbook(new File("D:/A对账/result/对账结果.xls"));

			Sheet sheet = wk.getSheet(0);
			List<String> innerList = new ArrayList<String>();
			if (sheet.getCell(1, 0).getContents().equals("")) {
				return;
			}
			for (int i = 0; i < sheet.getRows(); i++) {
				for (int j = 0; j < sheet.getColumns(); j++) {
					String contents = sheet.getCell(j, i).getContents();
					if (!contents.isEmpty()) {
						innerList.add(contents);
					}
				}
				if (innerList.contains("爱信宝")) {
					i += 2;
					flag = false;
				}
				if (innerList.contains("自营店铺明细表")) {
					i += 2;
					flag = true;
				}
				if (innerList.size() > 2) {
					if (flag) {
						SystemRecord record = new SystemRecord();
						record.setTransactionId(innerList.get(0));
						record.setTransactionDate(innerList.get(1));
						record.setAmount(new BigDecimal(innerList.get(4)));
						record.setShopName(innerList.get(2));
						record.setGoods(string2Goods(innerList.get(3)));
						aList.add(record);
					} else {
						BankRecord record = new BankRecord();
						record.setTransactionDate(innerList.get(0));
						record.setAmount(new BigDecimal(innerList.get(1)));
						record.setSerialId(innerList.get(2));
						record.setPayType(PayType.getEnum(innerList.get(3)));
						bList.add(record);
					}
				}

				innerList.clear();
			}
			aList.sort(Comparator.comparing(SystemRecord::getTransactionDate));
			bList.sort(Comparator.comparing(BankRecord::getTransactionDate));
			if (wk != null) {
				wk.close();
			}
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (wk != null) {
				wk.close();
			}
		}
	}

	public void compare(File detailFile, File bankfile) {
		area.setText("开始进行比较\n");
		List<SystemRecord> a = readAFile(detailFile);
		List<BankRecord> b = readBFile(bankfile);
		for (int i = a.size() - 1; i > -1; i--) {
			for (int j = 0; j < b.size(); j++) {
				long date1 = string2Date(a.get(i).getTransactionDate()).getTime();
				long date2 = string2Date(b.get(j).getTransactionDate()).getTime();
				long between = date1 - date2;
				long day = between / (24 * 60 * 60 * 1000);
				long hour = (between / (60 * 60 * 1000) - day * 24);
				long min = ((between / (60 * 1000)) - day * 24 * 60 - hour * 60);
				long s = (between / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
				if (Math.abs(s / 1000) < 60) {
					BigDecimal aa = a.get(i).getAmount().abs();
					BigDecimal ba = b.get(j).getAmount().abs();

					if (aa.compareTo(ba) == 0) {
						a.remove(i);
						b.remove(j);
						break;
					}
				}
			}
		}
		area.append("比较完成\n");
		area.append("比较结果如下\n");
		if (!a.isEmpty()) {
			area.append("爱信宝\n");
			a.sort(Comparator.comparing(SystemRecord::getTransactionDate));
			aList.addAll(a);
			System.out.println("自营店铺明细表");
			a.stream().forEach(i -> {
				System.out.println(i.getTransactionDate() + "   " + i.getShopName() + "   " + i.getAmount() + "    "
						+ i.getGoods());
				area.append(i.getTransactionDate() + "   " + i.getShopName() + "   " + i.getGoods() + "   "
						+ i.getAmount() + "\n");
			});
		}
		if (!b.isEmpty()) {
			b.sort(Comparator.comparing(BankRecord::getTransactionDate));
			bList.addAll(b);
			System.out.println("爱信宝");
			b.stream().forEach(i -> {
				System.out.println(i.getTransactionDate() + "    " + i.getSerialId() + "   " + i.getAmount() + "   "
						+ i.getPayType());
				area.append(i.getTransactionDate() + "   " + i.getSerialId() + "   " + i.getAmount() + "\n");
			});
		}
	}

	public List<SystemRecord> readAFile(File file) {
		ArrayList<SystemRecord> results = new ArrayList<>();
		Workbook wk = null;
		try {
			wk = Workbook.getWorkbook(file);
			Sheet sheet = wk.getSheet(0);
			List<String> innerList = new ArrayList<String>();
			for (int i = 12; i < sheet.getRows(); i++) {
				for (int j = 0; j < sheet.getColumns(); j++) {
					String contents = sheet.getCell(j, i).getContents();
					if (!contents.isEmpty() && !contents.contains("减价")) {
						innerList.add(contents);
					}

				}
				if (innerList.contains("小计：")) {
					currentDate = currentDate == null ? string2Date(innerList.get(5)) : currentDate;
					int goodsNum = innerList.size() / 14;

					if (innerList.get(innerList.size() - 6).equals("0.00")) {
						SystemRecord record = new SystemRecord();
						record.setShopName(innerList.get(4));
						record.setTransactionDate(innerList.get(5));
						currentDate = string2Date(innerList.get(5));
						record.setTransactionId(innerList.get(8));
						record.setAliAmount(new BigDecimal(innerList.get(innerList.size() - 4)));
						record.setWechatAmount(new BigDecimal(innerList.get(innerList.size() - 5)));
						record.setAmount(new BigDecimal(innerList.get(innerList.size() - 7)));
						List<Good> goods = new ArrayList<>();
						for (int k = 0; k < goodsNum; k++) {
							Good good = new Good();
							good.setName(innerList.get(8 + (k * 14)));
							good.setNum(Integer.parseInt(innerList.get(9 + (k * 14))));
							good.setUnitprice(new BigDecimal(innerList.get(10 + k * 14)));
							good.setTotalprice(new BigDecimal(innerList.get(11 + k * 14)));
							goods.add(good);
						}
						record.setGoods(goods);
						results.add(record);

					}
					innerList.clear();
				}
			}
			System.out.println("当前日期为" + currentDate);
			System.out.println(results.size());
			if (wk != null) {
				wk.close();
			}
			return results;
		} catch (BiffException | IOException e) {
			area.setForeground(Color.red);
			area.append(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	public List<BankRecord> readBFile(File file) {
		ArrayList<BankRecord> results = new ArrayList<>();
		Workbook wk = null;
		try {
			wk = Workbook.getWorkbook(file);
			Sheet sheet = wk.getSheet(0);
			for (int i = 1; i < sheet.getRows() - 3; i++) {
				BankRecord record = new BankRecord();
				if (sheet.getCell(0, i).getContents() != ""
						&& string2Date(sheet.getCell(0, i).getContents()).before(
								string2Date(new SimpleDateFormat("yyyy-MM-dd").format(currentDate) + " 23:59:59"))
						&& string2Date(sheet.getCell(0, i).getContents()).after(
								string2Date(new SimpleDateFormat("yyyy-MM-dd").format(currentDate) + " 00:00:00"))) {
					record.setTransactionDate(sheet.getCell(0, i).getContents());
					record.setOrderId(sheet.getCell(1, i).getContents());
					record.setSerialId(sheet.getCell(2, i).getContents());
					record.setThirdOrderId(sheet.getCell(3, i).getContents());
					record.setPayType(PayType.getEnum(sheet.getCell(6, i).getContents()));
					record.setAmount(record.getPayType().getMeaning().equals(PayType.COUSUME.getMeaning())
							? new BigDecimal(sheet.getCell(7, i).getContents())
							: new BigDecimal(sheet.getCell(7, i).getContents()).negate());
					record.setTrading(sheet.getCell(5, i).getContents());
					results.add(record);
				}

			}
			System.out.println(results.size());
			if (wk != null) {
				wk.close();
			}
			return results;
		} catch (BiffException e) {
			area.setForeground(Color.red);
			area.append(e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			area.setForeground(Color.red);
			area.append(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	public Date string2Date(String date) {
		try {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Good> string2Goods(String args) {
		List<Good> goods = new ArrayList<>();
		String[] arrs = args.split("=");
		for (int i = 0; i < arrs.length / 4; i++) {
			Good g = new Good();
			g.setName(arrs[(1 + 4 * i)].split(",")[0].split(" ")[0]);
			g.setNum(Integer.parseInt(arrs[(2 + 4 * i)].split(",")[0]));
			g.setUnitprice(new BigDecimal(arrs[(3 + 4 * i)].split(",")[0]));
			g.setTotalprice(new BigDecimal(arrs[(4 + 4 * i)].split(",")[0]));
		}
		return goods;
	}
}
