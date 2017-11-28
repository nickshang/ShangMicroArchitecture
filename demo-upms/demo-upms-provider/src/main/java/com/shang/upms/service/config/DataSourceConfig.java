package com.shang.upms.service.config;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 数据库配置类
 * Created by Think on 2017/6/30.
 */
@Component
@ConfigurationProperties(prefix = "spring.datasource",locations = "classpath:application.yml")
@Data
public class DataSourceConfig {

    private String driverClassName;

    private String url;

    private String username;

    private String password;

    private int maxActive;

    private int  minIdle;

    private int initialSize;

    private int maxWait;

    private int timeBetweenEvictionRunsMillis;

    private int minEvictableIdleTimeMillis;

    private String validationQuery;

    private boolean testWhileIdle;

    private boolean testOnBorrow;

    private boolean testOnReturn;

    private String filters;

    private String connectionProperties;

    private boolean poolPreparedStatements;

    private int maxPoolPreparedStatementPerConnectionSize;

}
