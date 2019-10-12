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
package com.example.demo.GUITest;

import java.awt.Color;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.example.demo.service.ReadExcel;

/**
 * 
 * @author min_huang
 *
 */
public class CheckAccountFrame {

	public static JFrame frame;
	public static List<File> detailFiles = new ArrayList<File>();
	public static File bankFile = null;

	public static void main(String[] args) {
		new CheckAccountFrame();
	}

	public CheckAccountFrame() {

		frame = new JFrame();
		frame.setTitle("check account tool");
		frame.setBackground(new Color(235, 242, 249));
		frame.setBackground(Color.white);
		frame.setBounds(600, 300, 500, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		frame.setSize(450, 330);
		JLabel label1 = new JLabel("自营店铺明细表");
		JButton button1 = new JButton("选择文件");
		JLabel label2 = new JLabel("爱信宝交易");
		JButton button2 = new JButton("选择文件");
		TextField tf1 = new TextField();

		TextField tf2 = new TextField();

		label1.setSize(100, 30);
		label1.setLocation(20, 20);
		button1.setSize(90, 30);
		button1.setLocation(320, 20);
		label2.setSize(100, 30);
		label2.setLocation(20, 70);
		button2.setSize(90, 30);
		button2.setLocation(320, 70);

		tf1.setLocation(90, 20);
		tf1.setSize(220, 30);
		tf2.setLocation(80, 70);
		tf2.setSize(230, 30);

		tf1.setColumns(30);
		tf2.setColumns(30);

		button1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				JFileChooser jfc = new JFileChooser();
				jfc.setMultiSelectionEnabled(true);
				// 显示打开的文件对话框
				jfc.showSaveDialog(frame);
				try {
					// 获取自营店铺明细表
					File[] files = jfc.getSelectedFiles();
					String fileName = "";
					if (files.length > 0) {

						for (int i = 0; i < files.length; i++) {
							detailFiles.add(files[i]);
							fileName += files[i].getName() + " ,";
						}
						tf1.setText(fileName);
					}

				} catch (Exception e2) {
					JPanel panel3 = new JPanel();
					JOptionPane.showMessageDialog(panel3, "没有选中任何文件", "提示", JOptionPane.WARNING_MESSAGE);
				}

			}

		});

		button2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				JFileChooser jfc = new JFileChooser();
				jfc.setMultiSelectionEnabled(true);
				// 显示打开的文件对话框
				jfc.showSaveDialog(frame);
				try {
					// 获取自营店铺明细表
					if (jfc.getSelectedFile() != null) {
						bankFile = jfc.getSelectedFile();
						tf2.setText(bankFile.getName());
					}
				} catch (Exception e2) {
					JPanel panel3 = new JPanel();
					JOptionPane.showMessageDialog(panel3, "没有选中任何文件", "提示", JOptionPane.WARNING_MESSAGE);
				}

			}

		});
		JButton button3 = new JButton("开始");
		button3.setSize(90, 30);
		button3.setLocation(190, 120);
		JLabel label4 = new JLabel();
		JTextArea area = new JTextArea();
		area.setColumns(40);
		area.setSize(450, 220);
		area.setLocation(0, 160);

		button3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ReadExcel r = new ReadExcel(area);
				if (detailFiles.isEmpty()) {
					tf1.setText("请选择文件");
				} else if (bankFile == null) {
					tf2.setText("请选择文件");
				} else {
					label4.setForeground(Color.green);
					label4.setText("正在对比");
					for (File f : detailFiles) {
						if (f.getName().contains("自营店铺明细报表")) {

							r.compare(f, bankFile);

						}
					}

					r.writeExcel(r.aList, r.bList);

				}

			}
		});

		frame.add(label1);

		frame.add(tf1);
		frame.add(button1);
		frame.add(label2);

		frame.add(tf2);
		frame.add(button2);
		frame.add(button3);
		frame.add(label4);
		frame.add(area);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// frame.add(frame);

		frame.setVisible(true);
	}
}
