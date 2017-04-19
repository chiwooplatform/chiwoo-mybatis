package org.chiwooplatform.mybatis;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;

import org.chiwooplatform.sample.model.Code;
import org.junit.Test;

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
    @Sql(scripts = { "/schema.ddl" })
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
