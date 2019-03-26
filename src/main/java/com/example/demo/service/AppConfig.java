package com.example.demo.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class AppConfig  {

 JedisConnectionFactory jedisConnectionFactory() {
  return new JedisConnectionFactory();
 }

 @Bean
 RedisTemplate< String, Object > redisTemplate() {
  final RedisTemplate< String, Object > template =  new RedisTemplate< String, Object >();
  template.setConnectionFactory( jedisConnectionFactory() );
  template.setKeySerializer( new StringRedisSerializer() );
  template.setHashValueSerializer( new GenericToStringSerializer< Object >( Object.class ) );
  template.setValueSerializer( new GenericToStringSerializer< Object >( Object.class ) );
  return template;
 }

 @Bean
 RedisMessageListenerContainer keyExpirationListenerContainer(JedisConnectionFactory connectionFactory) {

  RedisMessageListenerContainer listenerContainer = new RedisMessageListenerContainer();
  listenerContainer.setConnectionFactory(connectionFactory);

  listenerContainer.addMessageListener((message, pattern) -> {

   // event handling comes here
   System.out.println(message.getBody());

  }, new PatternTopic("__keyevent@*__:expired"));

  return listenerContainer;
 }



}