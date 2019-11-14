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
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration
@MapperScan(basePackages = "com.talent518.demo.mapper", sqlSessionFactoryRef = "defaultSqlSessionFactory"/* , sqlSessionTemplateRef = "defaultSqlSessionTemplate" */)
public class DataSource1 {
	@Bean(name = "defaultDataSource")
	@ConfigurationProperties(prefix = "spring.datasource")
	@Primary
	public DataSource defaultDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "defaultSqlSessionFactory")
	@Primary
	public SqlSessionFactory dataSourceSqlSessionFactory(@Qualifier("defaultDataSource") DataSource datasource) throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(datasource);
		return bean.getObject();
	}

	@Bean(name = "defaultSqlSessionTemplate")
	@Primary
	public SqlSessionTemplate dataSourceSqlsessiontemplate(@Qualifier("defaultSqlSessionFactory") SqlSessionFactory sessionfactory) {
		return new SqlSessionTemplate(sessionfactory);
	}

	@Bean(name = "defaultTransactionManager")
	@Primary
	public DataSourceTransactionManager defaultTransactionManager(@Qualifier("defaultDataSource") DataSource datasource) {
		return new DataSourceTransactionManager(datasource);
	}
}