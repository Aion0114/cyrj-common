package com.cyrj.common.db;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.util.StringUtil;

@Configuration
public class DynamicDataSource extends AbstractRoutingDataSource{ 
	

    @Autowired 
    private Environment env;
    
	//默认数据源，也就是主库
    protected DataSource masterDataSource;

    //分库
    protected Map<Object, Object> targetDataSources;
	
    //数据源创建工厂
    @Autowired
    private DruidDataSourceFactory dataSourceFactory;

    // 保存动态创建的数据源
    private static final Map<Object,Object> targetDataSource = new HashMap<Object,Object>();
	
    @Override
    protected DataSource determineTargetDataSource() {
        // 根据数据库选择方案，拿到要访问的数据库
        String dbName = determineCurrentLookupKey();

        if(StringUtil.isEmpty(dbName)){
        	if(masterDataSource == null)
        	{
        		masterDataSource = dataSource();
        	}
            return masterDataSource;
        }

        // 根据数据库名字，从已创建的数据库中获取要访问的数据库
        DataSource dataSource = (DataSource) targetDataSource.get(dbName);

        //如果没有对应数据源 则创建一个对应的数据源
        if(null == dataSource){
            dataSource = createDataSource(dbName);
        }

        if(dataSource == null) {
            throw new IllegalStateException("Cannot determine target DataSource for lookup daName [" + dbName + "]");
        } else {
            return dataSource;
        }
    }

    @Override
    protected String determineCurrentLookupKey() {
        String dataSourceName = DataSourceHolder.getDataSource();
        return dataSourceName;
    }

    public void addTargetDataSource(String key, DataSource dataSource) {
        DynamicDataSource.targetDataSource.put(key, dataSource);
    }
    public void removeTargetDataSource(String key) {
    	DynamicDataSource.targetDataSource.remove(key);
    }

    /**
     * 创建数据源
     * @param dbName
     * @return
     */
    private DataSource createDataSource(String dbName){
        DataSource dataSource = dataSourceFactory.createDataSource(dbName);
        if(null!= dataSource) {
            setDataSource(dbName, dataSource);
        }
        return dataSource;
    }

    public synchronized void removeDataSource(String dbName){
      this.removeTargetDataSource(dbName);
    }


    public void changDataSource(String dbKey){
        DataSourceHolder.setDataSource(dbKey);
    }

    public void setDataSource(String type, DataSource dataSource) {
        this.addTargetDataSource(type, dataSource);
        DataSourceHolder.setDataSource(type);
    }
    
    /**
     * 该方法重写为空，因为AbstractRoutingDataSource类中会通过此方法将，targetDataSources变量中保存的数据源交给resolvedDefaultDataSource变量
     * 在本方案中动态创建的数据源保存在了本类的targetDataSource变量中。如果不重写该方法为空，会因为targetDataSources变量为空报错
     * 如果仍然想要使用AbstractRoutingDataSource类中的变量保存数据源，则需要在每次数据源变更时，调用此方法来为resolvedDefaultDataSource变量更新
     */
    @Override
    public void afterPropertiesSet() {
    }
    
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(env.getProperty("spring.datasource.druid.url"));
        dataSource.setUsername(env.getProperty("spring.datasource.druid.username"));//用户名
        dataSource.setPassword(env.getProperty("spring.datasource.druid.password"));//密码
        dataSource.setDriverClassName(env.getProperty("spring.datasource.druid.driver-class-name"));
        dataSource.setInitialSize(Integer.parseInt(env.getProperty("spring.datasource.druid.initial-size")));
        dataSource.setMaxActive(Integer.parseInt(env.getProperty("spring.datasource.druid.max-active")));
        dataSource.setMinIdle(Integer.parseInt(env.getProperty("spring.datasource.druid.min-idle")));
        dataSource.setMaxWait(Integer.parseInt(env.getProperty("spring.datasource.druid.max-wait")));
        dataSource.setValidationQuery(env.getProperty("spring.datasource.druid.validation-query")); 
        dataSource.setTestOnBorrow(false);
        dataSource.setTestWhileIdle(true);
        dataSource.setPoolPreparedStatements(false);
        return dataSource;
    }

    //清除数据源
    public static void clearDataSource(String dbName) {
        targetDataSource.remove(dbName);
        DataSourceHolder.clearDataSource();
    }
}
