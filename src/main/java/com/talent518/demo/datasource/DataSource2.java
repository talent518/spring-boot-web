package com.talent518.demo.datasource;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration
@MapperScan(basePackages = "com.talent518.demo.mapper2", sqlSessionFactoryRef = "db2SqlSessionFactory"/* , sqlSessionTemplateRef = "db2SqlSessionTemplate" */)
public class DataSource2 {
	@Bean(name = "db2DataSource")
	@ConfigurationProperties(prefix = "spring.datasource2")
	public DataSource db2DataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "db2SqlSessionFactory")
	public SqlSessionFactory db2SqlSessionFactory(@Qualifier("db2DataSource") DataSource datasource) throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(datasource);
		return bean.getObject();
	}

	@Bean(name = "db2SqlSessionTemplate")
	public SqlSessionTemplate db2Sqlsessiontemplate(@Qualifier("db2SqlSessionFactory") SqlSessionFactory sessionfactory) {
		return new SqlSessionTemplate(sessionfactory);
	}

	@Bean(name = "db2TransactionManager")
	public DataSourceTransactionManager db2TransactionManager(@Qualifier("db2DataSource") DataSource datasource) {
		return new DataSourceTransactionManager(datasource);
	}
}