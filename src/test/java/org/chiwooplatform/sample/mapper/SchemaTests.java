/**
 * @author seonbo.shim
 * @version 1.0, 2017-04-06
 * @copyright BESPIN GLOBAL
 */
package org.chiwooplatform.sample.mapper;

import javax.sql.DataSource;

import org.chiwooplatform.mybatis.AbstractMybatisTests;
import org.chiwooplatform.sample.model.Code;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;

public class SchemaTests
    extends AbstractMybatisTests<Code> {

    /**
     * @see org.chiwooplatform.mybatis.AbstractMybatisTests#model()
     */
    @Override
    protected Code model() {
        return null;
    }

    @Autowired
    private DataSource dataSource;

    @Test
    @Sql(scripts = { "/schema.sql" })
    public void testCreateSchema()
        throws Exception {
        logger.info( "dataSource: {}", dataSource );
    }

    @Test
    @Rollback(false)
    @Sql(value = { "/schema-imports.sql" })
    public void testLoadData()
        throws Exception {
        logger.info( "dataSource: {}", dataSource );
    }
}
