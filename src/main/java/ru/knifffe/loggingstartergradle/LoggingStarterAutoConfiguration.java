package ru.knifffe.loggingstartergradle;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import ru.knifffe.loggingstartergradle.aspect.LogExecutionAspect;
import ru.knifffe.loggingstartergradle.webfilter.WebLoggingFilter;
import ru.knifffe.loggingstartergradle.webfilter.WebLoggingRequestBodyAdvice;

@AutoConfiguration
public class LoggingStarterAutoConfiguration {

    @Bean
    public LogExecutionAspect logExecutionAspect() {
        return new LogExecutionAspect();
    }

    @Bean
    public WebLoggingFilter webLoggingFilter() {
        return new WebLoggingFilter();
    }

    @Bean
    public WebLoggingRequestBodyAdvice webLoggingRequestBodyAdvice() {
        return new WebLoggingRequestBodyAdvice();
    }
}
