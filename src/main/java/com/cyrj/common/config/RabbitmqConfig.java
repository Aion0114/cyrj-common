//package com.cyrj.common.config;
//
//
//import org.springframework.amqp.core.*;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class RabbitmqConfig {
//
//    @Bean(name="logDirectQueue")
//    public Queue logDirectQueue() {
//        return new Queue("logDirectQueue");
//    }
//
//	@Bean(name="b2testmessage")
//    public Queue b2testMessage() {
//        return new Queue("topic.b2testmessage");
//    }
//
//    @Bean(name="message")
//    public Queue queueMessages() {
//        return new Queue("topic.message");
//    }
//
//	@Bean(name="integralMessage")
//    public Queue queueMessageIntegral() {
//        return new Queue("topic.integralMessage");
//    }
//
//    @Bean(name="syncmessage")
//    public Queue syncmessage() {
//        return new Queue("topic.syncmessage");
//    }
//
//    @Bean(name="b2upload")
//    public Queue b2upload() {
//        return new Queue("topic.b2upload");
//    }
//
//    @Bean(name="b2uploadBigData")
//    public Queue b2uploadBigData() {
//        return new Queue("topic.b2uploadBigData");
//    }
//
//    @Bean(name="s5GoodsDataSync")
//    public Queue s5GoodsDataSync() {
//        return new Queue("topic.s5GoodsDataSync");
//    }
//
//    @Bean(name="ysyPdaMessage")
//    public Queue ysyPdaMessage() {
//        return new Queue("topic.ysyPdaMessage");
//    }
//
//    @Bean(name="messageinventory")
//    public Queue messageinventory() {
//        return new Queue("topic.messageinventory");
//    }
//
//    @Bean(name="thirdPartyMessage")
//    public Queue thirdPartyMessage() {
//        return new Queue("topic.thirdPartyMessage");
//    }
//
//    @Bean(name="messagebillscheck")
//    public Queue messagebillscheck() {
//        return new Queue("topic.messagebillscheck");
//    }
//
//    @Bean(name="balanceMonthMessage")
//    public Queue balanceMonthMessage() {
//        return new Queue("topic.balanceMonthMessage");
//    }
//
//    @Bean
//    public TopicExchange exchange() {
//        return new TopicExchange("exchange");
//    }
//
//    @Bean
//    public DirectExchange logDirectExchange() {
//        return new DirectExchange("log.exchange.direct", false, false);
//    }
//
//    /**
//     * 根据路由键绑定队列到交换器上
//     *
//     * @return
//     */
//    @Bean
//    public Binding logDirectBinding(@Qualifier("logDirectQueue") Queue logDirectQueue, DirectExchange logDirectExchange) {
//        return BindingBuilder.bind(logDirectQueue).to(logDirectExchange).with("logDirectQueue");
//    }
//
//
//    @Bean
//    Binding bindingExchangeMessage(@Qualifier("b2testmessage") Queue b2testMessage, TopicExchange exchange) {
//        return BindingBuilder.bind(b2testMessage).to(exchange).with("topic.b2testmessage");
//    }
//
//    @Bean
//    Binding bindingExchangeMessages(@Qualifier("message") Queue queueMessages, TopicExchange exchange) {
//        return BindingBuilder.bind(queueMessages).to(exchange).with("topic.message");//*表示一个词,#表示零个或多个词
//    }
//
//    @Bean
//    Binding bindingExchangeMessage1(@Qualifier("integralMessage") Queue queueMessageIntegral, TopicExchange exchange) {
//        return BindingBuilder.bind(queueMessageIntegral).to(exchange).with("topic.integralMessage");
//    }
//
//    @Bean
//    Binding bindingExchangeMessage2(@Qualifier("syncmessage") Queue syncmessage, TopicExchange exchange) {
//        return BindingBuilder.bind(syncmessage).to(exchange).with("topic.syncmessage");
//    }
//
//    @Bean
//    Binding bindingExchangeMessage3(@Qualifier("b2upload") Queue b2upload, TopicExchange exchange) {
//        return BindingBuilder.bind(b2upload).to(exchange).with("topic.b2upload");
//    }
//
//    @Bean
//    Binding bindingExchangeMessage8(@Qualifier("b2uploadBigData") Queue b2uploadBigData, TopicExchange exchange) {
//        return BindingBuilder.bind(b2uploadBigData).to(exchange).with("topic.b2uploadBigData");
//    }
//
//    @Bean
//    Binding bindingExchangeMessage9(@Qualifier("s5GoodsDataSync") Queue s5GoodsDataSync, TopicExchange exchange) {
//        return BindingBuilder.bind(s5GoodsDataSync).to(exchange).with("topic.s5GoodsDataSync");
//    }
//
//    @Bean
//    Binding bindingExchangeMessageYsyPdaMessage(@Qualifier("ysyPdaMessage") Queue ysyPdaMessage, TopicExchange exchange) {
//        return BindingBuilder.bind(ysyPdaMessage).to(exchange).with("topic.ysyPdaMessage");
//    }
//
//    @Bean
//    Binding bindingExchangeMessage4(@Qualifier("messageinventory") Queue messageinventory, TopicExchange exchange) {
//        return BindingBuilder.bind(messageinventory).to(exchange).with("topic.messageinventory");
//    }
//
//    @Bean
//    Binding bindingExchangeMessage5(@Qualifier("thirdPartyMessage") Queue thirdPartyMessage, TopicExchange exchange) {
//        return BindingBuilder.bind(thirdPartyMessage).to(exchange).with("topic.thirdPartyMessage");
//    }
//
//    @Bean
//    Binding bindingExchangeMessage6(@Qualifier("messagebillscheck") Queue messagebillscheck, TopicExchange exchange) {
//        return BindingBuilder.bind(messagebillscheck).to(exchange).with("topic.messagebillscheck");
//    }
//
//    @Bean
//    Binding bindingExchangeMessage7(@Qualifier("balanceMonthMessage") Queue balanceMonthMessage, TopicExchange exchange) {
//        return BindingBuilder.bind(balanceMonthMessage).to(exchange).with("topic.balanceMonthMessage");
//    }
//
//}
//
