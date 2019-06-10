package com.example.demo.config.db;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author FCX
 * @date Create in 14:51 2019/5/8
 * @description sqlServer数据源配置
 */
@Configuration
@MapperScan(basePackages = {"com.example.demo.mssql.mapper.*"}, sqlSessionFactoryRef = "mssqlSessionFactory")
public class SqlServerDataSourceeConfig {
    @Value("${spring.datasource.dsqlserver.type}")
    private String type;

    @Value("${spring.datasource.dsqlserver.driverClassName}")
    private String driverClass;

    @Value("${spring.datasource.dsqlserver.jdbcUrl}")
    private String url;

    @Value("${spring.datasource.dsqlserver.username}")
    private String username;

    @Value("${spring.datasource.dsqlserver.password}")
    private String password;

    @Bean(value = "mssqlDataSource")
    public DataSource dataSource(){
        DruidDataSource datasource = new DruidDataSource();
        datasource.setDbType(type);
        datasource.setDriverClassName(driverClass);
        datasource.setUrl(url);
        datasource.setUsername(username);
        datasource.setPassword(password);
        return datasource;
    }

    @Bean(name = "mssqlTransactionManager")
    public DataSourceTransactionManager masterTransactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean(name = "mssqlSessionFactory")
    public SqlSessionFactory mssqlSessionFactory(@Qualifier("mssqlDataSource") DataSource dataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        //分页插件
        Interceptor interceptor = new PageInterceptor();
        Properties properties = new Properties();
        //数据库
        properties.setProperty("helperDialect", "sqlserver2012");
        //是否分页合理化
        properties.setProperty("reasonable", "false");

        interceptor.setProperties(properties);

        sessionFactory.setPlugins(new Interceptor[] {interceptor});
        sessionFactory.setDataSource(dataSource);
        return sessionFactory.getObject();
    }

    @Bean(name = "mssqlSessionTemplate")
    public SqlSessionTemplate mssqlSessionTemplate(@Qualifier("mssqlSessionFactory") SqlSessionFactory sqlSessionFactory){
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
