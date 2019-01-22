package com.dcits.paramManage.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class PropertiesUtil {

	private static final Log log = LogFactory.getLog(PropertiesUtil.class);

	private static final String PROPERTIES_URL = "config/config.properties";
	// 热加载时间
	private static final int LOAD_INTERVAL_SECONDS = 120;
	private static Properties properties = new Properties();

	// 静态块中的内容会在类别加载的时候先被执行
	static {
		loadFile(PROPERTIES_URL);
		monitor();
	}
	
	//加载配置文件
	private static void loadFile(String fileUrl) {
		// ClassLoader.getResourceAsStream只能用相对于classpath的绝对路径
		InputStream is = PropertiesUtil.class.getClassLoader().getResourceAsStream(fileUrl);
		try {
			properties.load(is);
		} catch (IOException e) {
			log.error("IOException when load" + fileUrl,e);
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				log.error("IOException when close InputStream" + fileUrl,e);
				e.printStackTrace();
			}
		}
	}
	
	//守护线程每隔LOAD_INTERVAL_SECONDS秒去加载一次properties
	private static void monitor(){
		Thread thread=new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					TimeUnit.SECONDS.sleep(LOAD_INTERVAL_SECONDS);
					loadFile(PROPERTIES_URL);
				} catch (InterruptedException e) {
					log.error("properties monitor thread Interrupted", e);;
				}
			}
		},"properties-monitor-thread");
		//设置为守护线程后，用户线程结束，此线程立即中断
		thread.setDaemon(true);
		thread.start();
		
	}
	
	public static String getProperty(String key) {
		return properties.getProperty(key);
	}
}
