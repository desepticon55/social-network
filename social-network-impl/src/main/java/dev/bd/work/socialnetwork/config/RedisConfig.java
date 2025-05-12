/*
 * VTB Group. Do not reproduce without permission in writing.
 * Copyright (c) 2025 VTB Group. All rights reserved.
 */

package dev.bd.work.socialnetwork.config;

import dev.bd.work.socialnetwork.service.PostListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.stream.ReadOffset;
import org.springframework.data.redis.connection.stream.StreamOffset;
import org.springframework.data.redis.stream.StreamMessageListenerContainer;
import org.springframework.data.redis.stream.Subscription;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.time.Duration;

/**
 * @author Alexey Bodyak
 */
@Slf4j
@Configuration
public class RedisConfig {

//    @Bean
//    public Subscription messageListenerContainer(RedisConnectionFactory connectionFactory) {
//        var options = StreamMessageListenerContainer.StreamMessageListenerContainerOptions.builder()
//                .pollTimeout(Duration.ofSeconds(1))
//                .targetType(String.class)
//                .executor(redisStreamThreadPoolTaskExecutor())
//                .build();
//
//        var container = StreamMessageListenerContainer.create(connectionFactory, options);
//        var subscription = container.receive(StreamOffset.create("post:111", ReadOffset.lastConsumed()), new PostListener());
//
//        container.start();
//        return subscription;
//    }
//
//    @Bean("redisStreamThreadPoolTaskExecutor")
//    public ThreadPoolTaskExecutor redisStreamThreadPoolTaskExecutor() {
//        int coreSize = Runtime.getRuntime().availableProcessors() + 1;
//        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
//        taskExecutor.setCorePoolSize(coreSize);
//        taskExecutor.setMaxPoolSize(coreSize);
//        taskExecutor.setQueueCapacity(100);
//        taskExecutor.setThreadNamePrefix("my-redis-stream-");
//        taskExecutor.setRejectedExecutionHandler((r, executor) ->
//                log.error("RejectedExecutionHandler: runnable={} executor={}", r, executor));
//        // If not initialized, the actuator cannot be found
//        taskExecutor.initialize();
//        return taskExecutor;
//    }


}
