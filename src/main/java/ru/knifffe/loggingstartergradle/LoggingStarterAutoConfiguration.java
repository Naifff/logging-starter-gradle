package ru.knifffe.loggingstartergradle;

import feign.Logger;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import ru.knifffe.loggingstartergradle.aspect.LogExecutionAspect;
import ru.knifffe.loggingstartergradle.feign.FeignRequestLogger;
import ru.knifffe.loggingstartergradle.service.LoggingService;
import ru.knifffe.loggingstartergradle.webfilter.WebLoggingFilter;
import ru.knifffe.loggingstartergradle.webfilter.WebLoggingRequestBodyAdvice;

@AutoConfiguration
@ConditionalOnProperty(prefix = "logging", value = "enabled", havingValue = "true", matchIfMissing = true)
public class LoggingStarterAutoConfiguration {

    @Bean
    @ConditionalOnProperty(prefix = "logging", value = "log-exec-time", havingValue = "true")
    public LogExecutionAspect logExecutionAspect() {
        return new LogExecutionAspect();
    }

    @Bean
    @ConditionalOnProperty(prefix = "logging.web-logging", value = "enabled", havingValue = "true", matchIfMissing = true)
    public WebLoggingFilter webLoggingFilter() {
        return new WebLoggingFilter();
    }

    @Bean
    @ConditionalOnProperty(prefix = "logging.web-logging", value = {"enabled", "log-body"}, havingValue = "true")
    public WebLoggingRequestBodyAdvice webLoggingRequestBodyAdvice() {
        return new WebLoggingRequestBodyAdvice();
    }

    @Bean
    public LoggingService loggingService() {
        return new LoggingService();
    }

    @Bean
    @ConditionalOnProperty(prefix = "logging.web-logging", value = "log-feign-requests", havingValue = "true")
    public FeignRequestLogger feignRequestLogger() {
        return new FeignRequestLogger();
    }

    @Bean
    @ConditionalOnProperty(prefix = "logging.web-logging", value = "log-feign-requests", havingValue = "true")
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.BASIC;
    }
}
