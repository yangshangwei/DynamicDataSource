package com.artisan.extractService;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Service;

import com.artisan.dynamicDB.DataSource;


/**
 * 
 * 
 * @ClassName: ExtractDataService
 * 
 * @Description: 业务类，这里暂时作为测试多数据源切换用
 * 
 * @author: Mr.Yang
 * 
 * @date: 2017年7月24日 下午9:07:38
 */

@Service
public class ExtractDataService {

	private static final Logger logger = LogManager
			.getLogger(ExtractDataService.class.getName());

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	/**
	 * 
	 * 
	 * @Title: selectDataFromPR
	 * 
	 * @Description:
	 * 
	 * 
	 * @return: void
	 */
	@DataSource(name = DataSource.PR_RB)
	public void selectDataFromPR_RB() {
		String sql = "select  subs_id from owe_event_charge  where event_inst_id = 10229001 ";

		jdbcTemplate.query(sql, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				logger.info(rs.getInt("subs_id"));
			}
		});
	}

	@DataSource(name = DataSource.DR_RB)
	public void selectDataFromDR_RB() {
		// 改为通过注解指定DB
		// DynamicDataSourceHolder.setDataSource(DBContextHolder.DATA_SOURCE_DR);
		String sql = " select  a.task_comments  from nm_task_type a  where a.task_name = 'ALARM_LOG_LEVEL' ";
		jdbcTemplate.query(sql, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				logger.info(rs.getString("task_comments"));
			}
		});
	}

	@DataSource(name = DataSource.PR_CC)
	public void selectDataFromPR_CC() {
		// DBContextHolder.setDataSource(DBContextHolder.DATA_SOURCE_CC);
		String sql = "select  acc_nbr  from acc_nbr  where  acc_nbr_id = 82233858 ";
		jdbcTemplate.query(sql, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				logger.info(rs.getString("acc_nbr"));
			}
		});

	}
}