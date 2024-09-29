package com.cabservice.cab_finder_service.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.GenericToStringSerializer;

import com.cabservice.cab_finder_service.Service.RideNotificationListener;

@Configuration

public class RedisConfig {
	
	@Bean
	public JedisConnectionFactory connectionFactory() {
		RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
		configuration.setHostName("localhost");
		configuration.setPort(6379);

		return new JedisConnectionFactory(configuration);
	}

	@Bean
//	@Primary
	public RedisTemplate<String, Object> redisTemplate() {
		RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
		
		template.setConnectionFactory(connectionFactory());
				
		template.setValueSerializer(new GenericToStringSerializer<Object>(Object.class));
		
		return template;
	}
	
	
	@Bean
	public RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter) {
		RedisMessageListenerContainer container = new RedisMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.addMessageListener(listenerAdapter, new ChannelTopic("rideNotifications"));
		return container;
	}
	
	@Bean
	public MessageListenerAdapter listenerAdapter(RideNotificationListener listener) {
		return new MessageListenerAdapter(listener, "onMessage");
	}
	@Bean
	ChannelTopic topic() {
		return new ChannelTopic("rideNotifications");
	}
}
