package com.louis.mango.admin.config;

import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.Servlet;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;



@Configuration
@EnableConfigurationProperties(DruidDataSourceProperties.class)
public class DruidConfig {

	@Autowired
	private DruidDataSourceProperties properties;
	
	@Bean
	@ConditionalOnMissingBean
	public DataSource druidDataSource() {
		DruidDataSource druidDataSource = new DruidDataSource();
		druidDataSource.setDriverClassName(properties.getDriverClassName());
        druidDataSource.setUrl(properties.getUrl());
        druidDataSource.setUsername(properties.getUsername());
        druidDataSource.setPassword(properties.getPassword());
        druidDataSource.setInitialSize(properties.getInitialSize());
        druidDataSource.setMinIdle(properties.getMinIdle());
        druidDataSource.setMaxActive(properties.getMaxActive());
        druidDataSource.setMaxWait(properties.getMaxWait());
        druidDataSource.setTimeBetweenEvictionRunsMillis(properties.getTimeBetweenEvictionRunsMillis());
        druidDataSource.setMinEvictableIdleTimeMillis(properties.getMinEvictableIdleTimeMillis());
        druidDataSource.setValidationQuery(properties.getValidationQuery());
        druidDataSource.setTestWhileIdle(properties.isTestWhileIdle());
        druidDataSource.setTestOnBorrow(properties.isTestOnBorrow());
        druidDataSource.setTestOnReturn(properties.isTestOnReturn());
        druidDataSource.setPoolPreparedStatements(properties.isPoolPreparedStatements());
        druidDataSource.setMaxPoolPreparedStatementPerConnectionSize(properties.getMaxPoolPreparedStatementPerConnectionSize());

        try {
            druidDataSource.setFilters(properties.getFilters());
            druidDataSource.init();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return druidDataSource;
	}
	
	
	/***
	 * 
	 * @Description 
	 *	注册Servlet信息，配置监控视图
	 *
	 *	本方法相当于web Servlet配置
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean
	public ServletRegistrationBean<Servlet> druidServlet() {
		ServletRegistrationBean<Servlet> servletRegistrationBean = new ServletRegistrationBean<Servlet>(new StatViewServlet(), "/druid/*");
		// 白名单
		servletRegistrationBean.addInitParameter("allow", "127.0.0.1,139.195.1.1");
		// IP黑名单（共同存在时，deny优先于allow）
		// 如果满足deny的话提示：sorry，you are not permitted to view....
		servletRegistrationBean.addInitParameter("deny", "192.168.1.145");
		// 登录查看信息的账号密码，用于登录Druid监控后台
		servletRegistrationBean.addInitParameter("loginUsername", "admin");
		servletRegistrationBean.addInitParameter("loginPassword", "admin");
		// 是否能够重置数据
		servletRegistrationBean.addInitParameter("resetEnable", "true");
		return servletRegistrationBean;
	}
	
	
	/***
	 * 
	 * @Description 
	 *	注册filter信息，监控拦截器
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean
	public FilterRegistrationBean<Filter> filterRegistrationBean() {
		FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<Filter>();
        filterRegistrationBean.setFilter(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
	}
}
