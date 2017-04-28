package org.chiwooplatform.mybatis;

/**
 * This class is for testing mybatis mapper.
 */
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import org.chiwooplatform.mybatis.dialect.MariaDBDialect;
import org.chiwooplatform.mybatis.plugin.LoggingInterceptor;
import org.chiwooplatform.mybatis.plugin.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;

@SpringBootConfiguration
@Import({ MybatisApplication.MybatisConfiguration.class })
@ImportAutoConfiguration(classes = {
    DataSourceAutoConfiguration.class,
    MybatisAutoConfiguration.class,
    DataSourceTransactionManagerAutoConfiguration.class })
@MapperScan(basePackages = "org.chiwooplatform.sample.mapper")
public class MybatisApplication {

    @Configuration
    static class MybatisConfiguration {

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
}
