package org.chiwooplatform.mybatis.dialect;
/**
 * @author <a href='mailto:lamp.java@gmail.com'>aider</a>
 */
public interface Dialect
{
    char NL = '\n';

    boolean supportsLimit();

    boolean supportsLimitOffset();

    String getLimitedSQL( String sql, int offset, int scale );
}