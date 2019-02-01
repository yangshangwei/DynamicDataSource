package com.artisan.dynamicDB;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 
 * 
 * @ClassName: DynamicDataSource
 * 
 * @Description: 
 *               AbstractRoutingDataSource中的抽象方法determineCurrentLookupKey是实现数据源的route的核心
 *               .需要重写该方法
 * 
 * @author: Mr.Yang
 * 
 * @date: 2017年7月24日 下午8:28:46
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

	@Override
	protected Object determineCurrentLookupKey() {
		return DynamicDataSourceHolder.getDataSource();
	}
}
