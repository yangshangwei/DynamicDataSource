package com.artisan.dynamicDB;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * 
 * @ClassName: DataSource https://www.bbsmax.com/A/KE5Q063JLG/
 *             http://linhongyu.blog.51cto.com/6373370/1615895
 *             https://www.bbsmax.com/A/8Bz8NEX5xg/
 * 
 * 
 * @Description: 注解@DataSource既可以加在方法上，也可以加在接口或者接口的实现类上，优先级别：方法>实现类>接口。
 *               如果接口、接口实现类以及方法上分别加了@DataSource注解来指定数据源，则优先以方法上指定的为准。
 * 
 * @author: Mr.Yang
 * 
 * @date: 2017年7月24日 下午9:59:29
 */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSource {
	// 和配置文件中 dynamicDatasourceMap中的key保持一致
	public static String PR_RB = "dataSourcePR";

	public static String DR_RB = "dataSourceDR";

	public static String PR_CC = "dataSourceCC";

	/**
	 * 
	 * 
	 * @Title: name
	 * 
	 * @Description: 如果仅标注@DataSource 默认为PR_RB数据库实例
	 * 
	 * @return
	 * 
	 * @return: String
	 */
	String name() default DataSource.PR_RB;

}
