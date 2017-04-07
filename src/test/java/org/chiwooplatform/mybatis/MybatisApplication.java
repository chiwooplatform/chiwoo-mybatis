package org.chiwooplatform.mybatis;

import org.chiwooplatform.mybatis.LoggingInterceptor;
import org.chiwooplatform.mybatis.PaginationInterceptor;
import org.chiwooplatform.mybatis.dialect.MariaDBDialect;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * This class is for testing mybatis mapper.
 */
@MapperScan(basePackages = "org.chiwooplatform.sample.mapper")
@SpringBootApplication
public class MybatisApplication {

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        final PaginationInterceptor  interceptor= new PaginationInterceptor();
        interceptor.setDialect( new MariaDBDialect() );
        return interceptor;
    }

    @Bean
    public LoggingInterceptor loggingInterceptor() {
        return new LoggingInterceptor();
    }
}
