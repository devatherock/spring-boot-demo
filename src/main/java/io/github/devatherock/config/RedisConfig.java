package io.github.devatherock.config;

import java.time.Duration;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import lombok.extern.slf4j.Slf4j;

@Configuration
public class RedisConfig {

	@Bean
	public JedisConnectionFactory jedisConnectionFactory() {
		RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
		config.setHostName("localhost");
		config.setPort(6379);

		JedisClientConfiguration clientConfig = JedisClientConfiguration
				.builder()
				.usePooling()
				.build();

		return new JedisConnectionFactory(config, clientConfig);
	}

	@Bean
	public RedisTemplate<Object, Object> redisTemplate() {
		RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<Object, Object>();
		redisTemplate.setConnectionFactory(jedisConnectionFactory());
		redisTemplate.setDefaultSerializer(new StringRedisSerializer());

		return redisTemplate;
	}
	
	@Bean
	public RedisCacheManagerBuilderCustomizer redisCacheManagerBuilderCustomizer() {
	    return (builder) -> builder
	      .withCacheConfiguration("testCache",
	        RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofSeconds(5)))
	      .withCacheConfiguration("messageCache",
	        RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(1000L)))
	      .cacheWriter(RedisCacheWriter.nonLockingRedisCacheWriter(jedisConnectionFactory()));
	}
	
	@Slf4j
	@Configuration
	public static class CacheNamesLogger {
		
		@Autowired
		private CacheManager cacheManager;
		
		@PostConstruct
		public void init() {
			cacheManager.getCacheNames().forEach(cacheName -> {
				LOGGER.info("Cache: {}", cacheName);
			});
		}
	}
}
