package org.chiwooplatform.mybatis.handler;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import org.apache.ibatis.type.DateTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;

/**
 * @author <a href='mailto:lamp.java@gmail.com'>aider</a>
 */
@MappedJdbcTypes(JdbcType.TIMESTAMP)
public class UTCDateTypeHandler
    extends DateTypeHandler {
    private static final String TIME_ZONE = "UTC";

    @Override
    public void setNonNullParameter( PreparedStatement ps, int i, Date parameter, JdbcType jdbcType )
        throws SQLException {
        Timestamp stamp = new Timestamp( ( parameter ).getTime() );
        // ps.setTimestamp( i, stamp );
        Calendar cal = Calendar.getInstance( TimeZone.getTimeZone( TIME_ZONE ) );
        ps.setTimestamp( i, stamp, cal );
    }

    @Override
    public Date getNullableResult( ResultSet rs, String columnName )
        throws SQLException {
        // Timestamp sqlTimestamp = rs.getTimestamp( columnName );
        Calendar cal = Calendar.getInstance( TimeZone.getTimeZone( TIME_ZONE ) );
        Timestamp sqlTimestamp = rs.getTimestamp( columnName, cal );
        if ( sqlTimestamp != null ) {
            return new Date( sqlTimestamp.getTime() );
        }
        return null;
    }

    @Override
    public Date getNullableResult( ResultSet rs, int columnIndex )
        throws SQLException {
        // Timestamp sqlTimestamp = rs.getTimestamp( columnIndex );
        Calendar cal = Calendar.getInstance( TimeZone.getTimeZone( TIME_ZONE ) );
        Timestamp sqlTimestamp = rs.getTimestamp( columnIndex, cal );
        if ( sqlTimestamp != null ) {
            return new Date( sqlTimestamp.getTime() );
        }
        return null;
    }

    @Override
    public Date getNullableResult( CallableStatement cs, int columnIndex )
        throws SQLException {
        // Timestamp sqlTimestamp = cs.getTimestamp( columnIndex );
        Calendar cal = Calendar.getInstance( TimeZone.getTimeZone( TIME_ZONE ) );
        Timestamp sqlTimestamp = cs.getTimestamp( columnIndex, cal );
        if ( sqlTimestamp != null ) {
            return new Date( sqlTimestamp.getTime() );
        }
        return null;
    }
}