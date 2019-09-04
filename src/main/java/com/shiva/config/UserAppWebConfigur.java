package com.shiva.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com.shiva" })
@PropertySource(value= {"classpath:application.properties"})
public class UserAppWebConfigur extends WebMvcConfigurerAdapter {
	@Autowired
	private Environment environment; 
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		String[] pathPatterns = { "/components/**", "/css/**", "/js/**"};
		String[] resourceLocations = {"/components/", "/css/", "/js/"};
		registry.addResourceHandler(pathPatterns).addResourceLocations(resourceLocations);
	}
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	public HttpEntity<String> httpEntity() {
		HttpHeaders handler=new HttpHeaders();
		handler.set("Authorization", environment.getProperty("ASANA_ACCESS_TOKEN"));
		HttpEntity<String> entity = new HttpEntity<String>("body", handler);
		return entity;
		
	}
}
