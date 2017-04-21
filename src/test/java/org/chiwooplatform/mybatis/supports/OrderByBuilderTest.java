/**
 * @author seonbo.shim
 * @version 1.0, 2017-04-21
 * @copyright BESPIN GLOBAL
 */
package org.chiwooplatform.mybatis.supports;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OrderByBuilderTest {

    protected final Logger logger = LoggerFactory.getLogger( this.getClass() );

    @Test
    public void generate()
        throws Exception {
        String orderby = OrderByBuilder.build( "no,title:desc,age-desc,reg_dtm:desc" );
        logger.info( "orderby: {}", orderby );
    }
}
