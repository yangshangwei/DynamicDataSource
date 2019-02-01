package com.artisan.dynamicDB;

/**
 * 
 * 
 * @ClassName: DynamicDataSourceHolder
 * 
 * @Description:创建DynamicDataSourceHolder用于持有当前线程中使用的数据源标识
 * 
 * @author: Mr.Yang
 * 
 * @date: 2017年7月24日 下午8:23:50
 */
public class DynamicDataSourceHolder {

	/**
	 * 数据源标识保存在线程变量中，避免多线程操作数据源时互相干扰
	 */
	private static final ThreadLocal<String> dataSourceHolder = new ThreadLocal<String>();

	/**
	 * 
	 * 
	 * @Title: setDataSource
	 * 
	 * @Description: 设置数据源
	 * 
	 * @param dataSource
	 * 
	 * @return: void
	 */
	public static void setDataSource(String dataSource) {
		dataSourceHolder.set(dataSource);
	}

	/**
	 * 
	 * 
	 * @Title: getDataSource
	 * 
	 * @Description: 获取数据源
	 * 
	 * @return
	 * 
	 * @return: String
	 */
	public static String getDataSource() {
		return dataSourceHolder.get();
	}

	/**
	 * 
	 * 
	 * @Title: clearDataSource
	 * 
	 * @Description: 清除数据源
	 * 
	 * 
	 * @return: void
	 */
	public static void clearDataSource() {
		dataSourceHolder.remove();
	}
}
