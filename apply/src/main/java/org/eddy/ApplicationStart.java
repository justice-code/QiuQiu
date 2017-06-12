package org.eddy;

import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.system.ApplicationPidFileWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.Properties;

/**
 * Created by eddy on 2017/6/6.
 */
@SpringBootApplication
@EnableTransactionManagement
public class ApplicationStart {

    @Autowired
    private DataSourceProperties dataSourceProperties;

    @Bean
    public DataSource definitionDataSource() throws Exception {
        StatFilter statFilter = new StatFilter();
        statFilter.setSlowSqlMillis(100);
        statFilter.setLogSlowSql(true);

        Properties config = new Properties();
        config.setProperty("url", dataSourceProperties.getUrl());
        config.setProperty("username", dataSourceProperties.getUsername());
        config.setProperty("password", dataSourceProperties.getPassword());
        config.setProperty("driverClassName", dataSourceProperties.getDriverClassName());

        config.setProperty("filters", "slf4j");
        DruidDataSource dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(config);
        dataSource.setProxyFilters(Collections.singletonList(statFilter));

        return dataSource;
    }

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(ApplicationStart.class);
        springApplication.addListeners(new ApplicationPidFileWriter("qiuqiu.pid"));
        springApplication.run(args);
    }
}
