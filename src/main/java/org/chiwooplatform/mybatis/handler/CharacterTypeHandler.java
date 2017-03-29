package org.chiwooplatform.mybatis.handler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;

/**
 * @author <a href='mailto:lamp.java@gmail.com'>aider</a>
 */
@MappedJdbcTypes(JdbcType.VARCHAR)
public class CharacterTypeHandler
    extends BaseTypeHandler<Object>
{
    public void setNonNullParameter( PreparedStatement ps, int i, Character parameter, JdbcType jdbcType )
        throws SQLException
    {
        ps.setString( i, parameter.toString() );
    }

    public String getNullableResult( ResultSet rs, String columnName )
        throws SQLException
    {
        return rs.getString( columnName );
    }

    public String getNullableResult( CallableStatement cs, int columnIndex )
        throws SQLException
    {
        return cs.getString( columnIndex );
    }

    @Override
    public void setNonNullParameter( PreparedStatement ps, int i, Object parameter, JdbcType jdbcType )
        throws SQLException
    {
        ps.setString( i, parameter.toString() );
    }

    @Override
    public String getNullableResult( ResultSet rs, int columnIndex )
        throws SQLException
    {
        return rs.getString( columnIndex );
    }
}