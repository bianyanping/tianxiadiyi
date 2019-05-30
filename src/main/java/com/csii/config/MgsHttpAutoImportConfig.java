package com.csii.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alipay.gateway.http.springmvc.EnableMgwHttp;
import com.alipay.gateway.http.springmvc.MgwHttpController;
import com.alipay.gateway.http.springmvc.MgwHttpResolver;

@Configuration
@EnableMgwHttp
public class MgsHttpAutoImportConfig {
	@Value("${system.name}")
	private String systemName;
	 
	@Bean(initMethod = "init")
	public MgwHttpResolver mgwHttpResolver() {
	  MgwHttpResolver resolver = new MgwHttpResolver();
	  resolver.setSysName(systemName);
	  return resolver;
	}
	 
	@Bean
	public MgwHttpController mgwHttpController() {
	  return new MgwHttpController();
	}
}
