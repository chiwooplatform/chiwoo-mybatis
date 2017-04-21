package org.chiwooplatform.mybatis.supports;

import java.util.Map;

import org.chiwooplatform.mybatis.mapper.BaseMapper;

/**
 * rule
 * {@link BaseMapper#ORDERBY} = title,age,reg_dtm:desc
 */
public class OrderByBuilder {

    private static final String COLUMN_DELIMITER = ",";

    private static final String ORDER_DELIMITER = "[:=\\-]";

    private static final String DESC = "desc";

    private final StringBuilder builder = new StringBuilder();

    private OrderByBuilder() {
    }

    int count = 0;

    static private OrderByBuilder create() {
        return new OrderByBuilder();
    }

    public OrderByBuilder asc( String columnName ) {
        if ( count == 0 ) {
            this.builder.append( "order by " ).append( columnName );
        } else {
            this.builder.append( ", " ).append( columnName );
        }
        ++count;
        return this;
    }

    public OrderByBuilder desc( String columnName ) {
        if ( count == 0 ) {
            this.builder.append( "order by " ).append( columnName ).append( " desc" );
        } else {
            this.builder.append( ", " ).append( columnName ).append( " desc" );
        }
        ++count;
        return this;
    }

    public String build() {
        return this.builder.toString();
    }

    /**
     * 
     * @param orderbyParameter It has stirng contcatnation rule. eg: title,age,reg_dtm:desc
     * @return order-by select sql query.
     */
    static public String build( String orderbyParameter ) {
        if ( orderbyParameter == null || orderbyParameter.length() < 1 ) {
            return null;
        }
        OrderByBuilder builder = OrderByBuilder.create();
        String values = orderbyParameter.replaceAll( " ", "" );
        String[] arr = values.split( COLUMN_DELIMITER );
        for ( String kv : arr ) {
            String[] kvarr = kv.split( ORDER_DELIMITER );
            String key = kvarr[0];
            if ( kvarr.length > 1 ) {
                String order = kvarr[1];
                if ( DESC.equalsIgnoreCase( order ) ) {
                    builder.desc( key );
                } else {
                    builder.asc( key );
                }
            } else {
                builder.asc( key );
            }
        }
        return builder.build();
    }

    static public String build( Map<String, Object> param ) {
        return build( (String) param.get( BaseMapper.ORDERBY ) );
    }
}
