package com.blemobi.weibo.core;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.stereotype.Service;

import com.blemobi.weibo.consul.ConsulManager;
import com.blemobi.weibo.global.Constant;

@Service
public class StartupListener implements ServletContextListener {
	public void contextInitialized(ServletContextEvent sce) {
		long consulIntervalTime = Constant.getConsulIntervaltime();

		System.out.println("Starting Consul Server ...");
		ConsulManager.startService(new String[] { "-env", "test" }, consulIntervalTime);

		ConsulManager.addConsulChangeListener(Constant.getAdapter());

		//ConsulManager.addConsulChangeListener(AppKeys.getAdapter());

	}

	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub

	}
}