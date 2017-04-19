package org.chiwooplatform.mybatis;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

/**
 * <pre>
 * SPRING-BOOT 프로젝트에 테스트 컨텍스트가 @SpringBootTest 으로 통합 되었다.
 * &#64;SpringApplicationConfiguration(classes=MyConfig.class) 에서 @SpringBootTest(classes=MyConfig.class) 으로
 * &#64;ContextConfiguration(classes=MyConfig.class, loader=SpringApplicationContextLoader.class) 에서 @SpringBootTest(classes=MyConfig.class) 으로
 * &#64;IntegrationTest 에서 @SpringBootTest(webEnvironment=WebEnvironment.NONE)
 * &#64;IntegrationTest 와 함께 @WebAppConfiguration 에서 @SpringBootTest(webEnvironment=WebEnvironment.DEFINED_PORT or RANDOM_PORT)
 * 
 * org.springframework.boot.test.TestRestTemplate 에서 org.springframework.boot.test.web.client.TestRestTemplate 으로
 * </pre>
 */
@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MybatisApplication.class)
public abstract class AbstractMybatisTests<T> {

    protected final transient Logger logger = LoggerFactory.getLogger( this.getClass() );

    protected static final String RESOURCE_DIR = "./src/test/resources/files";

    private final Integer AUTH_USER_ID = -100001;

    protected Integer userid() {
        return this.AUTH_USER_ID;
    }

    @Before
    public void setMDC() {
        MDC.put( "TXID", Long.toString( System.currentTimeMillis() ) );
    }

    @After
    public void removeMDC() {
        MDC.remove( "TXID" );
    }

    @Test
    public void contextLoads() {
    }

    abstract protected T model();
}
