package org.chiwooplatform.sample.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.apache.ibatis.type.Alias;

@SuppressWarnings("serial")
@Alias("parameterMap")
public class ParameterMap
    implements Serializable {

    final Map<String, Object> map = new HashMap<String, Object>();

    public ParameterMap() {
        super();
    }

    public ParameterMap( Map<String, Object> map ) {
        super();
        this.map.putAll( map );
    }

    public ParameterMap putAll( Map<String, Object> map ) {
        this.map.putAll( map );
        return this;
    }

    public ParameterMap put( String key, Object value ) {
        this.map.put( key, value );
        return this;
    }

    public Map<String, Object> map() {
        return this.map;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public ParameterMap parameterMap( Object key ) {
        Object v = map.get( key );
        if ( v instanceof Map ) {
            ParameterMap param = new ParameterMap();
            param.putAll( (Map) v );
            return param;
        }
        return null;
    }

    public Object get( Object key ) {
        Object v = map.get( key );
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

    public String val( final String key ) {
        return getString( key );
    }

    public boolean containsKey( String key ) {
        return map.containsKey( key );
    }

    public String getString( final String key ) {
        if ( !containsKey( key ) || get( key ) == null ) {
            return null;
        }
        Object value = get( key );
        if ( value instanceof String ) {
            return (String) value;
        } else {
            return value.toString();
        }
    }

    /**
     * @param key
     * @return
     */
    public Integer getInteger( final String key ) {
        if ( !containsKey( key ) || get( key ) == null ) {
            return null;
        }
        Object value = get( key );
        if ( value instanceof Integer ) {
            return (Integer) value;
        } else if ( value instanceof Double ) {
            return ( (Double) value ).intValue();
        } else if ( value instanceof Float ) {
            return ( (Float) value ).intValue();
        } else if ( value instanceof Long ) {
            return ( (Long) value ).intValue();
        } else if ( value instanceof BigDecimal ) {
            return ( (BigDecimal) value ).intValue();
        } else if ( value instanceof BigInteger ) {
            return ( (BigInteger) value ).intValue();
        } else if ( value instanceof String ) {
            return Integer.parseInt( (String) value );
        } else if ( value instanceof Boolean ) {
            if ( ( (Boolean) value ) ) {
                return 1;
            } else {
                return 0;
            }
        }
        return (Integer) value;
    }

    public Integer getInteger( final String key, final Integer defaultValue ) {
        Integer value = getInteger( key );
        if ( value == null ) {
            value = defaultValue;
        }
        return value;
    }

    /**
     * @param key
     * @return
     */
    public Double getDouble( final String key ) {
        if ( !containsKey( key ) || get( key ) == null ) {
            return null;
        }
        Object value = get( key );
        if ( value instanceof Double ) {
            return (Double) value;
        } else if ( value instanceof Integer ) {
            return ( (Integer) value ).doubleValue();
        } else if ( value instanceof Float ) {
            return ( (Float) value ).doubleValue();
        } else if ( value instanceof Long ) {
            return ( (Long) value ).doubleValue();
        } else if ( value instanceof BigDecimal ) {
            return ( (BigDecimal) value ).doubleValue();
        } else if ( value instanceof BigInteger ) {
            return ( (BigInteger) value ).doubleValue();
        } else if ( value instanceof String ) {
            return Double.parseDouble( (String) value );
        } else if ( value instanceof Boolean ) {
            if ( ( (Boolean) value ) ) {
                return 1.0;
            } else {
                return 0.0;
            }
        }
        return (Double) value;
    }

    /**
     * @param key
     * @return
     */
    public Long getLong( final String key ) {
        if ( !containsKey( key ) || get( key ) == null ) {
            return null;
        }
        Object value = get( key );
        if ( value instanceof Long ) {
            return (Long) value;
        } else if ( value instanceof Integer ) {
            return ( (Integer) value ).longValue();
        } else if ( value instanceof Double ) {
            return ( (Double) value ).longValue();
        } else if ( value instanceof Float ) {
            return ( (Float) value ).longValue();
        } else if ( value instanceof BigDecimal ) {
            return ( (BigDecimal) value ).longValue();
        } else if ( value instanceof BigInteger ) {
            return ( (BigInteger) value ).longValue();
        } else if ( value instanceof String ) {
            return Long.parseLong( (String) value );
        } else if ( value instanceof Boolean ) {
            if ( ( (Boolean) value ) ) {
                return 1L;
            } else {
                return 0L;
            }
        }
        return (Long) value;
    }

    public Boolean getBoolean( final String key ) {
        if ( !containsKey( key ) ) {
            return null;
        }
        Object value = get( key );
        if ( value instanceof Boolean ) {
            return (Boolean) value;
        }
        Integer intValue = getInteger( key );
        if ( intValue == 0 ) {
            return false;
        } else if ( intValue == 1 ) {
            return true;
        }
        return (Boolean) value;
    }

    public Date getDate( final String key ) {
        if ( !containsKey( key ) ) {
            return null;
        }
        Object value = get( key );
        if ( value instanceof Date ) {
            return (Date) value;
        } else if ( value instanceof Timestamp ) {
            return new Date( ( (Timestamp) value ).getTime() );
        } else if ( value instanceof Long ) {
            return new Date( (Long) value );
        }
        return (Date) value;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append( "{" );
        int i = 0;
        for ( String key : map.keySet() ) {
            String value = getString( key );
            if ( value != null && value.length() > 1000 ) {
                value = value.substring( 0, 1000 );
            }
            if ( i == 0 ) {
                builder.append( String.format( "%s: %s", key, value ) );
            } else {
                builder.append( String.format( ", %s: %s", key, value ) );
            }
            ++i;
        }
        builder.append( "}" );
        return builder.toString();
    }
}