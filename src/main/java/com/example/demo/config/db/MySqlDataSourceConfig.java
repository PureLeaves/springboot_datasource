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
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author FCX
 * @date Create in 11:16 2019/5/8
 * @description mysql数据源配置
 */
@Configuration
@MapperScan(basePackages={"com.example.demo.mysql.mapper.*"}, sqlSessionFactoryRef="mysqlSessionFactory")
public class MySqlDataSourceConfig {
    @Value("${spring.datasource.dmysql.type")
    private String type;

    @Value("${spring.datasource.dmysql.driverClassName}")
    private String driverClass;

    @Value("${spring.datasource.dmysql.jdbcUrl}")
    private String url;

    @Value("${spring.datasource.dmysql.username}")
    private String username;

    @Value("${spring.datasource.dmysql.password}")
    private String password;

    /**
     * 配置数据源基本信息
     */
    @Primary
    @Bean(value = "mysqlDataSource")
    public DataSource dataSource() {
        DruidDataSource datasource = new DruidDataSource();
        datasource.setDbType(type);
        datasource.setDriverClassName(driverClass);
        datasource.setUrl(url);
        datasource.setUsername(username);
        datasource.setPassword(password);
        return datasource;
    }

    @Bean(name = "mysqlTransactionManager")
    @Primary
    public DataSourceTransactionManager masterTransactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    @Primary
    @Bean(name = "mysqlSessionFactory")
    public SqlSessionFactory mysqlSessionFactory(@Qualifier("mysqlDataSource") DataSource dataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        //分页插件
        Interceptor interceptor = new PageInterceptor();
        Properties properties = new Properties();
        //数据库
        properties.setProperty("helperDialect", "mysql");

        //是否分页合理化
        properties.setProperty("reasonable", "false");

        interceptor.setProperties(properties);

        sessionFactory.setPlugins(new Interceptor[] {interceptor});
        sessionFactory.setDataSource(dataSource);
        return sessionFactory.getObject();
    }

    @Bean(name = "mysqlSessionTemplate")
    @Primary
    public SqlSessionTemplate mysqlSessionTemplate(@Qualifier("mysqlSessionFactory") SqlSessionFactory sqlSessionFactory){
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
