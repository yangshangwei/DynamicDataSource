package com.artisan.dynamicDB;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * 
 * 
 * @ClassName: DataSourceAspect
 * 
 * @Description: 
 *               定义AOP切面以便拦截所有带有注解@DataSource的方法，取出注解的值作为数据源标识放到DBContextHolder的线程变量中
 * 
 * @author: Mr.Yang
 * 
 * @date: 2017年7月25日 上午10:51:41
 */
public class DataSourceAspect {

	/**
	 * 
	 * 
	 * @Title: intercept
	 * 
	 * @Description: 拦截目标方法，获取由@DataSource指定的数据源标识，设置到线程存储中以便切换数据源
	 * 
	 * @param point
	 * @throws Exception
	 * 
	 * @return: void
	 */
	public void intercept(JoinPoint point) throws Exception {
		Class<?> target = point.getTarget().getClass();
		MethodSignature signature = (MethodSignature) point.getSignature();
		// 默认使用目标类型的注解，如果没有则使用其实现接口的注解
		for (Class<?> clazz : target.getInterfaces()) {
			resolveDataSource(clazz, signature.getMethod());
		}
		resolveDataSource(target, signature.getMethod());
	}

	/**
	 * 
	 * 
	 * @Title: resolveDataSource
	 * 
	 * @Description: 提取目标对象方法注解和类型注解中的数据源标识
	 * 
	 * @param clazz
	 * @param method
	 * 
	 * @return: void
	 */
	private void resolveDataSource(Class<?> clazz, Method method) {
		try {
			Class<?>[] types = method.getParameterTypes();
			// 默认使用类型注解
			if (clazz.isAnnotationPresent(DataSource.class)) {
				DataSource source = clazz.getAnnotation(DataSource.class);
				DynamicDataSourceHolder.setDataSource(source.name());
			}
			// 方法注解可以覆盖类型注解
			Method m = clazz.getMethod(method.getName(), types);
			if (m != null && m.isAnnotationPresent(DataSource.class)) {
				DataSource source = m.getAnnotation(DataSource.class);
				DynamicDataSourceHolder.setDataSource(source.name());
			}
		} catch (Exception e) {
			System.out.println(clazz + ":" + e.getMessage());
		}
	}
}
