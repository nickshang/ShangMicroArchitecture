package com.shang.upms.service.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Druid配置
 * Created by Think on 2017/6/30.
 */
@Configuration
@Slf4j
public class DruidConfig {

    private  DataSourceConfig dataSourceConfig;

    @Autowired
    public DruidConfig(DataSourceConfig  dataSourceConfig){
        this.dataSourceConfig = dataSourceConfig;
    }

    @Bean
    public ServletRegistrationBean druidServlet() {
        ServletRegistrationBean reg = new ServletRegistrationBean();
        reg.setServlet(new StatViewServlet());
        reg.addUrlMappings("/druid/*");
        reg.addInitParameter("loginUsername", "druid");
        reg.addInitParameter("loginPassword", "druid");
        return reg;
    }
    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        filterRegistrationBean.addInitParameter("profileEnable", "true");
        filterRegistrationBean.addInitParameter("principalCookieName", "USER_COOKIE");
        filterRegistrationBean.addInitParameter("principalSessionName", "USER_SESSION");
        return filterRegistrationBean;
    }

    @Bean(name = "druidDataSource", initMethod = "init", destroyMethod = "close")
    @Primary
    public DataSource druidDataSource(){
        DruidDataSource datasource = new DruidDataSource();

        System.out.println(this.dataSourceConfig);

        datasource.setUrl(this.dataSourceConfig.getUrl());
        datasource.setUsername(this.dataSourceConfig.getUsername());
        datasource.setPassword(this.dataSourceConfig.getPassword());
        datasource.setDriverClassName(this.dataSourceConfig.getDriverClassName());

        datasource.setInitialSize(this.dataSourceConfig.getInitialSize());
        datasource.setMinIdle(this.dataSourceConfig.getMinIdle());
        datasource.setMaxActive(this.dataSourceConfig.getMaxActive());
        datasource.setMaxWait(this.dataSourceConfig.getMaxWait());
        datasource.setTimeBetweenEvictionRunsMillis(this.dataSourceConfig.getTimeBetweenEvictionRunsMillis());
        datasource.setMinEvictableIdleTimeMillis(this.dataSourceConfig.getMinEvictableIdleTimeMillis());
        datasource.setValidationQuery(this.dataSourceConfig.getValidationQuery());
        datasource.setTestWhileIdle(this.dataSourceConfig.isTestWhileIdle());
        datasource.setTestOnBorrow(this.dataSourceConfig.isTestOnBorrow());
        datasource.setTestOnReturn(this.dataSourceConfig.isTestOnReturn());
        datasource.setPoolPreparedStatements(this.dataSourceConfig.isPoolPreparedStatements());
        try {
            datasource.setFilters(this.dataSourceConfig.getFilters());
        } catch (SQLException e) {
            log.error("druid configuration initialization filter", e);
        }
        return datasource;
    }

}