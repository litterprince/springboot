package com.example1.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = ClusterConfig.PACKAGE, sqlSessionFactoryRef = "clusterSqlSessionFactory")
public class ClusterConfig {
    @Autowired
    private Environment env;

    public static final String PACKAGE = "com.example1.clustermapper";
    //public static final String MAPPER_LOCATION = "classpath:mybatis/clustermapper/**/*.xml";

    @Bean(name="clusterDataSource")
    @ConfigurationProperties(prefix = "cluster.datasource")
    @Qualifier("clusterDataSource")
    public DataSource clusterDataSource(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(env.getProperty("cluster.datasource.driver-class-name"));
        dataSource.setUrl(env.getProperty("cluster.datasource.url"));
        dataSource.setUsername(env.getProperty("cluster.datasource.username"));
        dataSource.setPassword(env.getProperty("cluster.datasource.password"));
        dataSource.setInitialSize(2);//初始化时建立物理连接的个数
        dataSource.setMaxActive(20);//最大连接池数量
        dataSource.setMinIdle(0);//最小连接池数量
        dataSource.setMaxWait(60000);//获取连接时最大等待时间，单位毫秒。
        dataSource.setValidationQuery("SELECT 1");//用来检测连接是否有效的sql
        dataSource.setTestOnBorrow(false);//申请连接时执行validationQuery检测连接是否有效
        dataSource.setTestWhileIdle(true);//建议配置为true，不影响性能，并且保证安全性。
        dataSource.setPoolPreparedStatements(false);//是否缓存preparedStatement，也就是PSCache
        return dataSource;
    }

    //创建Session
    @Bean(name="clusterSqlSessionFactory")
    public SqlSessionFactory clusterSqlSessionFactory(@Qualifier("clusterDataSource") DataSource dataSource) throws Exception{
        final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
       /* Resource[] resource = new PathMatchingResourcePatternResolver().getResources(ClusterConfig.MAPPER_LOCATION);
        sqlSessionFactoryBean.setMapperLocations(resource);*/
        return sqlSessionFactoryBean.getObject();
    }
}
