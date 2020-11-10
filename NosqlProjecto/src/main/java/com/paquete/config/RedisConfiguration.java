package com.paquete.config;

import com.opentext.otag.sdk.types.v3.auth.ExpiringSession;
import com.paquete.model.LdapFailAwareRedisObjectSerializer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

public class RedisConfiguration {
    @Primary
    @Bean
    public RedisTemplate<String, ExpiringSession> redisTemplate(RedisConnectionFactory connectionFactory) {
      RedisTemplate<String, ExpiringSession> template = new RedisTemplate<String, ExpiringSession>();
  
      template.setKeySerializer(new StringRedisSerializer());
      template.setHashKeySerializer(new StringRedisSerializer());
      template.setHashValueSerializer(new LdapFailAwareRedisObjectSerializer());
  
      template.setConnectionFactory(connectionFactory);
      return template;
    }
}
