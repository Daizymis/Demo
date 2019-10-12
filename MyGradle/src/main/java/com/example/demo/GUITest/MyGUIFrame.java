package com.example.demo.GUITest;

import java.awt.BorderLayout;
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

import com.example.demo.service.ReadExcel;

/**
 * 
 * @author min_huang
 *
 */
public class MyGUIFrame {

	public static JFrame frmIpa;
	public static List<File> detailFiles = new ArrayList<File>();
	public static File bankFile = null;

	public static JFrame frame;

	public static void main(String[] args) {
		new MyGUIFrame();
	}

	public MyGUIFrame() {
		init();
	}

	public void init() {
		frmIpa = new JFrame();
		frmIpa.setTitle("对账工具");
		frmIpa.setBounds(600, 300, 500, 400);
		frmIpa.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// 面板1
		JPanel panel = new JPanel();
		frmIpa.getContentPane().add(panel, BorderLayout.NORTH);

		JLabel label1 = new JLabel("自营店铺明细表");

		JButton button1 = new JButton("选择文件");
		TextField tf1 = new TextField();
		tf1.setColumns(35);
		TextField tf2 = new TextField();
		tf2.setColumns(35);
		button1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				JFileChooser jfc = new JFileChooser();
				jfc.setMultiSelectionEnabled(true);
				// 显示打开的文件对话框
				jfc.showSaveDialog(frmIpa);
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

		JLabel label2 = new JLabel("爱信宝交易");
		JButton button2 = new JButton("选择文件");
		button2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				JFileChooser jfc = new JFileChooser();
				jfc.setMultiSelectionEnabled(true);
				// 显示打开的文件对话框
				jfc.showSaveDialog(frmIpa);
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
		JLabel label4 = new JLabel();
		button3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ReadExcel r = new ReadExcel();
				if (detailFiles.isEmpty()) {
					tf1.setText("请选择文件");
				} else if (bankFile == null) {
					tf2.setText("请选择文件");
				} else {
					label4.setForeground(Color.green);
					label4.setText("正在对比");
					for (File f : detailFiles) {
						if (f.getName().contains("自营店铺明细报表")) {
							r.compare(bankFile, f);
						}
					}
					label4.setText("正在生成文件");
					r.writeExcel(r.aList, r.bList);
					label4.setText("完成");
				}

			}
		});

		panel.add(label1);

		panel.add(tf1);
		panel.add(button1);
		panel.add(label2);

		panel.add(tf2);
		panel.add(button2);
		panel.add(button3);
		panel.add(label4);
		frmIpa.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmIpa.add(panel);

		frmIpa.setVisible(true);
	}
}
