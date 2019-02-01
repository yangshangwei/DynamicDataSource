package com.artisan;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.artisan.extractService.ExtractDataService;

/**
 * 
 * 
 * @ClassName: App
 * 
 * @Description: 入口类
 * 
 * @author: Mr.Yang
 * 
 * @date: 2017年7月24日 下午8:50:25
 */
public class App {
	public static void main(String[] args) {
		try {
			// 加载日志框架 log4j2
			LoggerContext context = (LoggerContext) LogManager
					.getContext(false);
			ResourceLoader loader = new PathMatchingResourcePatternResolver();
			Resource resource = loader.getResource("classpath:log4j2.xml");

			context.setConfigLocation(resource.getFile().toURI());

			// 加载spring配置信息
			ApplicationContext ctx = new ClassPathXmlApplicationContext(
					"classpath:spring-context.xml");
			// 从容器中获取Bean
			ExtractDataService service = ctx.getBean("extractDataService",
					ExtractDataService.class);
			// 从PR的RB实例中获取数据
			service.selectDataFromPR_RB();
			// 从DR的RB实例中获取数据
			service.selectDataFromDR_RB();
			// 从PR的CC实例中获取数据
			service.selectDataFromPR_CC();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}