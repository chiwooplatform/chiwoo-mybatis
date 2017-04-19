package org.chiwooplatform.mybatis;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import org.chiwooplatform.mybatis.dialect.MariaDBDialect;
import org.chiwooplatform.mybatis.plugin.LoggingInterceptor;
import org.chiwooplatform.mybatis.plugin.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;

/**
 * This class is for testing mybatis mapper.
 */
@MapperScan(basePackages = "org.chiwooplatform.sample.mapper")
@SpringBootApplication
public class MybatisApplication {

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        final PaginationInterceptor interceptor = new PaginationInterceptor();
        interceptor.setDialect( new MariaDBDialect() );
        return interceptor;
    }

    @Bean
    public LoggingInterceptor loggingInterceptor() {
        return new LoggingInterceptor();
    }
}
