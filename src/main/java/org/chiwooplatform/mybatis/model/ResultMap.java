package org.chiwooplatform.mybatis.model;

import java.util.HashMap;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.SQLException;

import org.apache.ibatis.type.Alias;

/**
 * @author <a href='mailto:lamp.java@gmail.com'>aider</a>
 */
@Alias("rmap")
public class ResultMap
    extends HashMap<String, Object> {

    private static final long serialVersionUID = -2706757925745599974L;

    public Object put( String key, Object value ) {
        return super.put( key.toLowerCase(), value );
    }

    public Integer integer( String key ) {
        Object value = get( key );
        if ( value == null ) {
            return null;
        }
        if ( value instanceof Integer ) {
            return (Integer) value;
        }
        if ( value instanceof String ) {
            return Integer.valueOf( (String) value );
        } else if ( value instanceof BigDecimal ) {
            return ( (BigDecimal) value ).intValue();
        } else if ( value instanceof BigInteger ) {
            return ( (BigInteger) value ).intValue();
        } else if ( value instanceof Long ) {
            return ( (Long) value ).intValue();
        }
        return (Integer) value;
    }

    public Integer integer( String key, Integer defaultValue ) {
        Integer v = integer( key );
        if ( v == null ) {
            v = defaultValue;
        }
        return v;
    }

    public String val( String key ) {
        Object v = get( key );
        return (String) v;
    }

    public Object get( Object key ) {
        Object v = super.get( key );
        if ( v instanceof java.sql.Clob ) {
            java.sql.Clob clob = (java.sql.Clob) v;
            if ( clob != null ) {
                try {
                    return clob.getSubString( 1, (int) clob.length() );
                } catch ( SQLException e ) {
                    return null;
                }
            }
        }
        return v;
    }
}