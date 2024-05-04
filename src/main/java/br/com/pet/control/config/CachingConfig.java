package br.com.pet.control.config;

import java.time.Duration;

import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager.RedisCacheManagerBuilder;
import org.springframework.data.redis.connection.RedisConnectionFactory;

import com.fasterxml.jackson.core.StreamReadConstraints.Builder;

@Configuration
@EnableCaching
public class CachingConfig {
	@Bean
    public CacheManager cacheManager(RedisConnectionFactory connectionFactory) {
				
		return RedisCacheManagerBuilder
        	.fromConnectionFactory(connectionFactory)
        	.withCacheConfiguration("listaPets",
                RedisCacheConfiguration.defaultCacheConfig()
                    .entryTtl(Duration.ofMinutes(10)) // TTL set to 10 minutes
                    
            )
            .build();
    }

  

		
}
