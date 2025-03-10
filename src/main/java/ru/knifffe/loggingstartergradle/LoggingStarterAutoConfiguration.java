package ru.knifffe.loggingstartergradle;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import ru.knifffe.loggingstartergradle.aspect.LogExecutionAspect;

@AutoConfiguration
public class LoggingStarterAutoConfiguration {

    @Bean
    public LogExecutionAspect logExecutionAspect() {
        return new LogExecutionAspect();
    }
}
