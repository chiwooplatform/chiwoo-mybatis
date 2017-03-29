package org.chiwooplatform.mybatis;

import java.util.Properties;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.MappedStatement.Builder;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.chiwooplatform.mybatis.dialect.Dialect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author <a href='mailto:lamp.java@gmail.com'>aider</a>
 */
@Intercepts({
    @Signature(type = Executor.class, method = "query", args = {
        MappedStatement.class,
        Object.class,
        RowBounds.class,
        ResultHandler.class }) })
public class PaginationInterceptor
    implements Interceptor {

    private final transient Logger log = LoggerFactory.getLogger( PaginationInterceptor.class );

    private final static int MAPPED_STATEMENT_INDEX = 0;

    private final static int PARAMETER_INDEX = 1;

    private final static int ROWBOUNDS_INDEX = 2;

    private final static int MAX_VALUE_CONDITION = RowBounds.NO_ROW_LIMIT;

    private Dialect dialect;

    public Object intercept( Invocation invocation )
        throws Throwable {
        final Object[] queryArgs = invocation.getArgs();
        if ( queryArgs[ROWBOUNDS_INDEX] != null ) {
            final RowBounds rowBounds = (RowBounds) queryArgs[ROWBOUNDS_INDEX];
            if ( rowBounds.getLimit() < MAX_VALUE_CONDITION ) {
                processIntercept( invocation.getArgs() );
            }
        }
        queryArgs[ROWBOUNDS_INDEX] = org.apache.ibatis.session.RowBounds.DEFAULT;
        return invocation.proceed();
    }

    private void processIntercept( final Object[] queryArgs ) {
        MappedStatement ms = (MappedStatement) queryArgs[MAPPED_STATEMENT_INDEX];
        Object parameter = queryArgs[PARAMETER_INDEX];
        final RowBounds rowBounds = (RowBounds) queryArgs[ROWBOUNDS_INDEX];
        if ( rowBounds != null ) {
            int offset = rowBounds.getOffset();
            int limit = rowBounds.getLimit();
            if ( dialect.supportsLimit() && ( offset != RowBounds.NO_ROW_OFFSET || limit != RowBounds.NO_ROW_LIMIT ) ) {
                log.trace( "Pagination query prepare ..." );
                BoundSql boundSql = ms.getBoundSql( parameter );
                String sql = boundSql.getSql().trim();
                if ( dialect.supportsLimitOffset() ) {
                    sql = dialect.getLimitedSQL( sql, offset, limit );
                    offset = RowBounds.NO_ROW_OFFSET;
                } else {
                    sql = dialect.getLimitedSQL( sql, 0, limit );
                }
                BoundSql newBoundSql = new BoundSql( ms.getConfiguration(), sql, boundSql.getParameterMappings(),
                                                     boundSql.getParameterObject() );
                log.trace( "Mybatis Page Search：" + sql.replaceAll( "\n", "" ) );
                MappedStatement newMs = copyFromMappedStatement( ms, new BoundSqlSqlSource( newBoundSql ) );
                queryArgs[MAPPED_STATEMENT_INDEX] = newMs;
            }
        }
        queryArgs[ROWBOUNDS_INDEX] = RowBounds.DEFAULT;
    }

    private MappedStatement copyFromMappedStatement( MappedStatement ms, SqlSource newSqlSource ) {
        Builder builder = new MappedStatement.Builder( ms.getConfiguration(), ms.getId(), newSqlSource,
                                                       ms.getSqlCommandType() );
        builder.resource( ms.getResource() );
        builder.fetchSize( ms.getFetchSize() );
        builder.statementType( ms.getStatementType() );
        builder.keyGenerator( ms.getKeyGenerator() );
        if ( ms.getKeyProperties() != null && ms.getKeyProperties().length > 0 ) {
            builder.keyProperty( ms.getKeyProperties()[0] );
        }
        builder.timeout( ms.getTimeout() );
        builder.parameterMap( ms.getParameterMap() );
        builder.resultMaps( ms.getResultMaps() );
        builder.resultSetType( ms.getResultSetType() );
        builder.cache( ms.getCache() );
        builder.flushCacheRequired( ms.isFlushCacheRequired() );
        builder.statementType( ms.getStatementType() );
        MappedStatement newMs = builder.build();
        return newMs;
    }

    public Object plugin( Object target ) {
        return Plugin.wrap( target, this );
    }

    public static class BoundSqlSqlSource
        implements SqlSource {

        BoundSql boundSql;

        public BoundSqlSqlSource( BoundSql boundSql ) {
            this.boundSql = boundSql;
        }

        public BoundSql getBoundSql( Object parameterObject ) {
            return boundSql;
        }
    }

    public void setProperties( Properties props ) {
    }

    public void setDialect( Dialect dialect ) {
        this.dialect = dialect;
    }
}