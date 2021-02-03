package com.redmaple.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.baomidou.mybatisplus.autoconfigure.SpringBootVFS;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.MybatisXMLLanguageDriver;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;

/**   
 * @Description: 没有测试 oacle 数据库，故注释
 * @author: uwank171 
 * @date: Feb 3, 2021 9:28:21 AM 
 *  
 */
//@Configuration
//@MapperScan(basePackages = "com.redmaple.dao.oracle", sqlSessionFactoryRef = "oracleSqlSessionFactory")
public class OracleDataSourceConfig {
	
	@Bean(name = "oracleDataSource")
	@ConfigurationProperties(prefix = "spring.datasource.druid.oracle")
	public DataSource dataSource() {
		 return DataSourceBuilder.create().build();
	}
	
	
	@Bean(name = "oracleSqlSessionFactory")
	public MybatisSqlSessionFactoryBean sqlSessionFactory(@Qualifier("oracleDataSource") DataSource dataSource) throws Exception{
		MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
		Properties properties = new Properties();
        properties.put("prefix", "");
        properties.put("blobType", "BLOB");
        properties.put("boolValue", "TRUE");
        
        properties.put("helperDialect", "oracle");
        properties.put("offsetAsPageNum", "true");
        properties.put("rowBoundsWithCount", "true");
        properties.put("reasonable", "true");
        properties.put("supportMethodsArguments","true");
        properties.put("params","pageNum=pageNum;pageSize=pageSize;");
        sqlSessionFactory.setConfigurationProperties(properties);
        sqlSessionFactory.setVfs(SpringBootVFS.class);
        sqlSessionFactory.setDataSource(dataSource);
        
        MybatisConfiguration configuration = new MybatisConfiguration();
        configuration.setDefaultScriptingLanguage(MybatisXMLLanguageDriver.class);
        configuration.setJdbcTypeForNull(JdbcType.NULL);
        sqlSessionFactory.setConfiguration(configuration);
//        sqlSessionFactory.setGlobalConfig(globalConfiguration());
//        sqlSessionFactory.setPlugins(new Interceptor[] { new PaginationInterceptor(), new PerformanceInterceptor(),
//                new OptimisticLockerInterceptor()
//                ,new DynamicTblnterceptor()
//                ,new DateAuthInterceptor()
//        });
        
//        sqlSessionFactory.setTypeAliasesPackage("com.redmaple.entity");
//        sqlSessionFactory.setMapperLocations(
//                new PathMatchingResourcePatternResolver().getResources("classpath:master/*.xml"));
//        sqlSessionFactory.setDataSource(dataSource);
		return sqlSessionFactory;
	}
	
    @Bean(name = "oracleTransactionManager")
    public DataSourceTransactionManager transactionManager(@Qualifier("oracleDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
  
    @Bean(name = "oracleSqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(
            @Qualifier("masterSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
	
	
	
	
	
	
	
	
	
	
	
	
}
