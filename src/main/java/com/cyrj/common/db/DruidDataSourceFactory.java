package com.cyrj.common.db;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.util.StringUtil;

@Component
public class DruidDataSourceFactory{


    @Autowired 
    private Environment env;
    
    //jdbcUrl前缀 拼接jdbcUrl需要用到
	@Value("${jdbc.jdbcUrlPrefix}") 
    private String jdbcUrlPrefix;

    //jdbcUrl前缀 拼接jdbcUrl需要用到
	@Value("${jdbc.jdbcUrlSuffix}") 
    private String jdbcUrlSuffix;
 
	@Value("${spring.datasource.druid.driver-class-name}") 
    private String driverClassName;

    //初始化池大小
	@Value("${spring.datasource.druid.initial-size}") 
    private int initialPoolSize;

    //连接池保持的最小连接数
	@Value("${spring.datasource.druid.min-idle}")
    private int minPoolSize;
	
	 //连接池中拥有的最大连接数，如果获得新连接时会使连接总数超过这个值则不会再获取新连接，
    // 而是等待其他连接释放，所以这个值有可能会设计地很大,default : 15
	@Value("${spring.datasource.druid.max-active}") 
    private int maxPoolSize;
	

    //获取连接等待超时时间
	@Value("${spring.datasource.druid.max-wait}") 
    private int maxWaitMillis;

    public DataSource createDataSource(Object o) {

    	DruidDataSource druidDataSource = null;
    	
    	JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource());
        final String dbName = (String) o;
        DataBaseInfo baseInfo = new DataBaseInfo();
        if(dbName.contains("mysqlipNew")) {
            if(dbName.contains("cdb-mchmkg8f.sg.cdb.myqcloud.com")) {
                baseInfo.setServerPort(Integer.parseInt(env.getProperty("mysqlxjpdb.serverPort")));
            } else{
                baseInfo.setServerPort(Integer.parseInt(env.getProperty("mysqldb.serverPort")));
            }
            baseInfo.setDbAccount(env.getProperty("mysqldb.username"));
            baseInfo.setDbPassword(env.getProperty("mysqldb.password"));
            baseInfo.setServerIp(dbName.replace("mysqlipNew",""));
            baseInfo.setDbName("dbmanager");
        } else if(dbName.contains("mysqlip")) {
            baseInfo.setDbAccount(env.getProperty("mysqldb.username"));
            baseInfo.setDbPassword(env.getProperty("mysqldb.password"));
            baseInfo.setServerPort(Integer.parseInt(env.getProperty("mysqldb.serverPort")));
            baseInfo.setServerIp(env.getProperty(dbName+".serverIp"));
            if(env.containsProperty(dbName+".dbname"))
            {
                baseInfo.setDbName(env.getProperty(dbName+".dbname"));
            }else{
                baseInfo.setDbName("dbmanager");
            }
        } else if(dbName.equals("yjc_scorecard")) {
        	baseInfo.setDbAccount(env.getProperty("mysqlyjcdb.username"));
            baseInfo.setDbPassword(env.getProperty("mysqlyjcdb.password"));
            baseInfo.setServerPort(Integer.parseInt(env.getProperty("mysqlyjcdb.serverPort")));
            baseInfo.setServerIp(env.getProperty("mysqlyjcdb.serverIp"));
            baseInfo.setDbName(env.getProperty("mysqlyjcdb.dbname"));
        } else {
            try {
                baseInfo = jdbcTemplate.queryForObject("select * from QDTenantInfo where dbName = '" + dbName + "' limit 1",
                        new RowMapper<DataBaseInfo>() {
                            @Override
                            public DataBaseInfo mapRow(ResultSet resultSet, int i) throws SQLException {
                                DataBaseInfo baseInfo = new DataBaseInfo();
                                baseInfo.setDbPassword(resultSet.getString("dbPassword"));
                                baseInfo.setDbName(resultSet.getString("dbName"));
                                baseInfo.setDbAccount(resultSet.getString("dbAccount"));
                                baseInfo.setServerIp(resultSet.getString("serverIp"));
                                baseInfo.setServerPort(resultSet.getInt("serverPort"));
                                return baseInfo;
                            }
                        });
            } catch (Exception e) {
                e.printStackTrace();
                baseInfo = null;
            }

            if (baseInfo == null) {
                try {
                    baseInfo =  jdbcTemplate.queryForObject("select * from qdtenantinfo_history where dbName = '" + dbName + "' limit 1",
                            new RowMapper<DataBaseInfo>() {
                                @Override
                                public DataBaseInfo mapRow(ResultSet resultSet, int i) throws SQLException {
                                    DataBaseInfo baseInfo = new DataBaseInfo();
                                    baseInfo.setDbPassword(resultSet.getString("dbPassword"));
                                    baseInfo.setServerPort(resultSet.getInt("serverPort"));
                                    baseInfo.setDbName(resultSet.getString("dbName"));
                                    baseInfo.setServerIp(resultSet.getString("serverIp"));
                                    baseInfo.setDbAccount(resultSet.getString("dbAccount"));
                                    return baseInfo;
                                }
                            }
                    );
                } catch (Exception e) {
                    e.printStackTrace();
                    baseInfo = null;
                }
            }
        }

        if (baseInfo == null) {
            return null;
        }

        druidDataSource = new DruidDataSource();
        druidDataSource.setFailFast(true);
        try {
			druidDataSource.setFilters("stat");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
        druidDataSource.setDriverClassName(driverClassName);
        String jdbcUrl = this.createJdbcUrl(baseInfo);
        druidDataSource.setUrl(jdbcUrl);
        druidDataSource.setUsername(baseInfo.getDbAccount());
        druidDataSource.setPassword(baseInfo.getDbPassword());
        druidDataSource.setInitialSize(initialPoolSize);
        druidDataSource.setMinIdle(minPoolSize);
        druidDataSource.setMaxActive(maxPoolSize);
        druidDataSource.setMaxWait(maxWaitMillis);
//        druidDataSource.setConnectionErrorRetryAttempts(0);
//        druidDataSource.setBreakAfterAcquireFailure(true);
        // 配置一旦重试多次失败后等待多久再继续重试连接，单位是毫秒
        druidDataSource.setTimeBetweenConnectErrorMillis(60000);

        return druidDataSource;
    }

    /**
     * 拼接jdbcUrl
     * @param baseInfo
     * @return
     */
    private String createJdbcUrl(DataBaseInfo baseInfo){

        String ip = baseInfo.getServerIp();
        int port = baseInfo.getServerPort();
        String dbName = baseInfo.getDbName();

        StringBuilder jdbcUrl = new StringBuilder("");
        jdbcUrl.append(jdbcUrlPrefix);
        jdbcUrl.append(ip);
        jdbcUrl.append(":");
        jdbcUrl.append(port);
        jdbcUrl.append("/");
        jdbcUrl.append(dbName);
        if(!StringUtil.isEmpty(jdbcUrlSuffix)){
            jdbcUrl.append(jdbcUrlSuffix);
        }

        return jdbcUrl.toString();
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

}
