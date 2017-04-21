package org.chiwooplatform.mybatis.plugin;

import java.util.Map;
import java.util.Properties;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.statement.PreparedStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import org.chiwooplatform.mybatis.dialect.Dialect;
import org.chiwooplatform.mybatis.mapper.BaseMapper;
import org.chiwooplatform.mybatis.supports.OrderByBuilder;
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

    private final transient Logger logger = LoggerFactory.getLogger( PaginationInterceptor.class );

    private final static int MAPPED_STATEMENT_INDEX = 0;

    private final static int PARAMETER_INDEX = 1;

    private final static int ROWBOUNDS_INDEX = 2;

    private final static int MAX_VALUE_CONDITION = RowBounds.NO_ROW_LIMIT;

    private Dialect dialect;

    public Object intercept( Invocation invocation )
        throws Throwable {
        final Object[] queryArgs = invocation.getArgs();
        Object parameter = queryArgs[PARAMETER_INDEX];
        Map<String, Object> param = null;
        if ( parameter != null && ( parameter instanceof Map<?, ?> ) ) {
            @SuppressWarnings("unchecked")
            Map<String, Object> paramObj = (Map<String, Object>) parameter;
            param = paramObj;
        }
        processTotalRowcount( invocation, param );
        if ( supportInterceptor( queryArgs ) ) {
            processIntercept( invocation, param );
        } else {
            queryArgs[ROWBOUNDS_INDEX] = org.apache.ibatis.session.RowBounds.DEFAULT;
        }
        return invocation.proceed();
    }

    private MappedStatement copyFromMappedStatement( MappedStatement ms, SqlSource newSqlSource ) {
        MappedStatement.Builder builder = new MappedStatement.Builder( ms.getConfiguration(), ms.getId(), newSqlSource,
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

    private boolean supportInterceptor( Object[] queryArgs ) {
        if ( queryArgs[ROWBOUNDS_INDEX] != null ) {
            final RowBounds rowBounds = (RowBounds) queryArgs[ROWBOUNDS_INDEX];
            if ( rowBounds.getLimit() < MAX_VALUE_CONDITION ) {
                return true;
            }
        }
        return false;
    }

    private PreparedStatement prepareStatement( StatementHandler handler, Executor executor, String sql )
        throws SQLException {
        PreparedStatement pstmt = executor.getTransaction().getConnection().prepareStatement( sql );
        handler.parameterize( pstmt );
        return pstmt;
    }

    private static boolean containsKey( Map<String, Object> param, final String key ) {
        if ( param == null ) {
            return false;
        }
        if ( param.containsKey( key ) ) {
            return true;
        }
        return false;
    }

    private void processTotalRowcount( Invocation invocation, Map<String, Object> param )
        throws SQLException {
        if ( containsKey( param, BaseMapper.TOTAL_ROWCOUNT ) ) {
            if ( invocation.getTarget() instanceof Executor ) {
                final Object[] queryArgs = invocation.getArgs();
                MappedStatement ms = (MappedStatement) queryArgs[MAPPED_STATEMENT_INDEX];
                Object parameter = queryArgs[PARAMETER_INDEX];
                final RowBounds rowBounds = (RowBounds) queryArgs[ROWBOUNDS_INDEX];
                Configuration config = ms.getConfiguration();
                BoundSql boundSql = ms.getBoundSql( parameter );
                Executor executor = (Executor) invocation.getTarget();
                String countSql = "select count(1) as cnt from ( " + boundSql.getSql() + " ) TMP_TOTAL_COUNT";
                // log.trace( "countSql: {}", countSql );
                BoundSql countBoundSql = new BoundSql( config, countSql, boundSql.getParameterMappings(),
                                                       boundSql.getParameterObject() );
                PreparedStatement pstmt = null;
                try {
                    PreparedStatementHandler handler = new PreparedStatementHandler( executor, ms, parameter, rowBounds,
                                                                                     null, countBoundSql );
                    pstmt = prepareStatement( handler, executor, countSql );
                    ResultSet rs = pstmt.executeQuery();
                    if ( rs.next() ) {
                        param.put( BaseMapper.TOTAL_ROWCOUNT, rs.getInt( 1 ) );
                    }
                } finally {
                    pstmt.close();
                }
            }
        }
    }

    private void processIntercept( Invocation invocation, Map<String, Object> param )
        throws SQLException {
        logger.trace( "Pagination query prepare ..." );
        final Object[] queryArgs = invocation.getArgs();
        MappedStatement ms = (MappedStatement) queryArgs[MAPPED_STATEMENT_INDEX];
        Object parameter = queryArgs[PARAMETER_INDEX];
        final RowBounds rowBounds = (RowBounds) queryArgs[ROWBOUNDS_INDEX];
        logger.trace( "invocation.getTarget(): {}", invocation.getTarget().getClass().getName() );
        logger.trace( "invocation.getArgs(): {}", parameter );
        logger.trace( "rowBounds: {}", rowBounds );
        Configuration config = ms.getConfiguration();
        BoundSql boundSql = ms.getBoundSql( parameter );
        Object parameterObject = boundSql.getParameterObject();
        String sql = boundSql.getSql().trim();
        if ( containsKey( param, BaseMapper.ORDERBY ) ) {
            final String orderBy = OrderByBuilder.build( param );
            if ( orderBy != null ) {
                sql = sql + '\n' + orderBy;
            }
        }
        int offset = rowBounds.getOffset();
        int limit = rowBounds.getLimit();
        if ( dialect.supportsLimit() && ( offset != RowBounds.NO_ROW_OFFSET || limit != RowBounds.NO_ROW_LIMIT ) ) {
            if ( dialect.supportsLimitOffset() ) {
                sql = dialect.getLimitedSQL( sql, offset, limit );
                offset = RowBounds.NO_ROW_OFFSET;
            } else {
                sql = dialect.getLimitedSQL( sql, 0, limit );
            }
            BoundSql newBoundSql = new BoundSql( config, sql, boundSql.getParameterMappings(), parameterObject );
            logger.trace( "Mybatis Page Search：" + sql.replaceAll( "\n", "" ) );
            MappedStatement newMs = copyFromMappedStatement( ms, new BoundSqlSqlSource( newBoundSql ) );
            queryArgs[MAPPED_STATEMENT_INDEX] = newMs;
        }
        queryArgs[ROWBOUNDS_INDEX] = RowBounds.DEFAULT;
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