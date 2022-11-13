package com.zyf.request.info.collector.core.common.redis;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.apache.logging.log4j.util.Strings;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.Map;

/**
 * @author IvanZhang
 */
public class RedisClient {
    private RedisTemplate<String,String> redisTemplate;

    public static RedisClient create(RedisConfig config){
        // 基本配置
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
        configuration.setHostName(config.getAddress());
        configuration.setPort(config.getPort());
        configuration.setDatabase(config.getDatabase());
        if (Strings.isNotBlank(config.getPassword())) {
            configuration.setPassword(RedisPassword.of(config.getPassword()));
        }
        // 连接池配置
        GenericObjectPoolConfig genericObjectPoolConfig = new GenericObjectPoolConfig();
        genericObjectPoolConfig.setMaxTotal(config.getMaxActive());
        genericObjectPoolConfig.setMaxWaitMillis(config.getMaxWait());
        genericObjectPoolConfig.setMaxIdle(config.getMaxIdle());
        genericObjectPoolConfig.setMinIdle(config.getMinIdle());

        // lettuce pool
        LettucePoolingClientConfiguration.LettucePoolingClientConfigurationBuilder builder = LettucePoolingClientConfiguration.builder();
        builder.poolConfig(genericObjectPoolConfig);
        builder.commandTimeout(Duration.ofSeconds(config.getTimeout()));
        LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory(configuration, builder.build());
        lettuceConnectionFactory.afterPropertiesSet();
        RedisClient redisClient = new RedisClient();
        redisClient.redisTemplate = createRedisTemplate(lettuceConnectionFactory);
        return redisClient;
    }

    private static <T> RedisTemplate<String, T> createRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, T> stringObjectRedisTemplate = new RedisTemplate<>();
        stringObjectRedisTemplate.setConnectionFactory(redisConnectionFactory);
        RedisSerializer<String> redisSerializer = new StringRedisSerializer();
        // key序列化
        stringObjectRedisTemplate.setKeySerializer(redisSerializer);
        // value序列化
        stringObjectRedisTemplate.setValueSerializer(redisSerializer);
        // value hashMap序列化
        stringObjectRedisTemplate.setHashValueSerializer(redisSerializer);
        // key haspMap序列化
        stringObjectRedisTemplate.setHashKeySerializer(redisSerializer);
        stringObjectRedisTemplate.afterPropertiesSet();
        return stringObjectRedisTemplate;
    }

    public void put(String key, Map<String,String> map){
        for (String propertyName: map.keySet()){
            redisTemplate.opsForHash().put(key,propertyName,map.get(propertyName));
        }
    }
}
