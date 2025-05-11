/*
 * VTB Group. Do not reproduce without permission in writing.
 * Copyright (c) 2025 VTB Group. All rights reserved.
 */

package dev.bd.work.socialnetwork.config;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Jackson config.
 *
 * @author Alexey Bodyak
 */
@Configuration
public class JacksonConfig {

    @Bean
    public Module javaTimeModule() {
        return new JavaTimeModule();
    }
}
