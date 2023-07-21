//package com.cyrj.common.config;
//
//import org.apache.commons.lang3.StringUtils;
//import org.apache.http.auth.UsernamePasswordCredentials;
//import org.apache.http.client.CredentialsProvider;
//import org.apache.http.impl.client.BasicCredentialsProvider;
//import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
//import org.apache.log4j.BasicConfigurator;
//import org.elasticsearch.client.RestClient;
//import org.elasticsearch.client.RestClientBuilder;
//import org.elasticsearch.client.RestHighLevelClient;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.cache.interceptor.KeyGenerator;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import org.apache.http.HttpHost;
//import org.apache.http.auth.AuthScope;
//
//import java.util.Arrays;
//import java.util.Objects;
//
//@Configuration
//public class EsConfig {
//
//    //权限验证
//    final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
//
//
//    @Value("${elasticsearch.address}")
//    private String address;
//
//    @Value("${elasticsearch.username}")
//    private String username;
//
//    @Value("${elasticsearch.password}")
//    private String password;
//
//
//    @Bean
//    public RestClientBuilder restClientBuilder() {
//
//
//        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
//        credentialsProvider.setCredentials(AuthScope.ANY,
//                new UsernamePasswordCredentials(username, password));
//        // 初始化 RestClient, hostName 和 port 填写集群的内网 VIP 地址与端口
//        RestClientBuilder builder = RestClient.builder(makeHttpHost(address));
//        // 设置认证信息
//        builder.setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
//            @Override
//            public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
//                return httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
//            }
//        });
//        return builder;
//    }
//
//
//    @Bean(name = "restHighLevelClient")
//    public RestHighLevelClient restHighLevelClient(@Autowired RestClientBuilder restClientBuilder) {
//        restClientBuilder.setMaxRetryTimeoutMillis(10000);
//        return new RestHighLevelClient(restClientBuilder);
//    }
//
//    /**
//     * 处理请求地址
//     * @param s
//     * @return HttpHost
//     */
//    private HttpHost makeHttpHost(String s) {
//        assert StringUtils.isNotEmpty(s);
//        String[] address = s.split(":");
//        String ip = address[1].replace("//","");
//        int port = Integer.parseInt(address[2]);
//        return new HttpHost(ip, port, address[0]);
//    }
//}
