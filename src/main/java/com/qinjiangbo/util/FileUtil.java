package com.qinjiangbo.util;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import com.qinjiangbo.enums.WriteMode;

public class FileUtil {
	
	private static final String ROOT_DIR = System.getProperty("user.dir");
	
	/**
	 * 将数据写入文件
	 * @param path 文件路径
	 * @param data 数据
	 * @param mode 写入模式 -- BufferedOutputStream, FileOutputStream, FileWriter
	 * @throws IOException
	 */
	public static void writeFile(String path, List<String> data, WriteMode mode) throws IOException {
		path = ROOT_DIR + path;
		File file = new File(path);
		if(!file.exists()) {
			file.createNewFile();
		}
		switch (mode) {
		case BufferedOutputStream:
			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
			for(String str : data) {
				bos.write(str.getBytes("utf-8"));
			}
			bos.flush();
			bos.close();
			break;
		case FileOutputStream:
			FileOutputStream fos = new FileOutputStream(file);
			for(String str : data) {
				fos.write(str.getBytes("utf-8"));
			}
			fos.flush();
			fos.close();
			break;
		case FileWriter:
			OutputStreamWriter writer = new FileWriter(file);
			for(String str : data) {
				writer.write(str);
			}
			writer.flush();
			writer.close();
			break;
		}
	}
	
	/**
	 * 一行一行读取数据
	 * @param path 数据路径
	 * @return 数据
	 * @throws IOException
	 */
	public static List<String> readFile(String path) throws IOException {
		List<String> data = new ArrayList<String>();
		path = ROOT_DIR + path;
		File file = new File(path);
		BufferedReader reader = null;
		if(file.exists()) {
			reader = new BufferedReader(new FileReader(file));
			String temp = null;
			while((temp = reader.readLine()) != null) {
				data.add(temp);
			}
		}else {
			throw new FileNotFoundException("data path is invalid!");
		}
		reader.close();
		return data;
	}
	
	/**
	 * 将数据写入文件
	 * @param path 文件路径
	 * @param data 数据
	 * @throws IOException
	 */
	public static void appendFile(String path, List<String> data) throws IOException {
		path = ROOT_DIR + path;
		File file = new File(path);
		if(!file.exists()) {
			file.createNewFile();
		}
		OutputStreamWriter writer = new FileWriter(file, true);
		for(String str : data) {
			writer.write(str);
		}
		writer.flush();
		writer.close();
	}
}
